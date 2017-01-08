package com.lami.tarsier.message;

import com.lami.tarsier.consumer.UserCache;
import com.lami.tarsier.bootstrap.SocketServer;
import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;


public abstract class BaseBusiness {
	protected static Logger logger = Logger.getLogger(BaseBusiness.class);
	
	protected UserCache userCache;
	protected SocketServer _socketServer;
	
	/** 被子类重写的方法  - 是否需要检验用户状态*/
	public abstract boolean checkUserBeanStatus();
	/** 被子类重写的方法  - 事件处理方法*/
	public abstract void execute(IoSession session, DefaultMessage msg);
	
	
	/**
	 * 校验用户的权限
	 * @return
	 */
	protected boolean isPermission(DefaultMessage defaultMessage){
		if(defaultMessage == null) return false;
		return true;
	}
	
	/**
	 * 关闭 mina 回话
	 * @param session
	 */
	protected void closeSession(IoSession session){
		try {
			session.close(true);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	/**
	 * server 回复 client
	 * @param session
	 * @param msgContent
	 */
	protected void replayClient(IoSession session, DefaultMessage DefaultMessage, String msgContent){
		DefaultMessage mhaoMsgReply = new DefaultMessage(DefaultMessage.getMsgName() + 1, DefaultMessage.getMsgSender(), msgContent);
		try {
			session.write(mhaoMsgReply);
			logger.info(mhaoMsgReply);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return ;
	}
	
	/** 主方法，实现对本类其它方法的调用 */  
	public void doExecute(IoSession session, DefaultMessage msg){
		if(checkUserBeanStatus()){
			DefaultMessage mhaoDefaultMessage = (DefaultMessage)msg;
			try {
				if(!isPermission(mhaoDefaultMessage)){
					logger.info("error 用户 ("+ mhaoDefaultMessage.getMsgSender()+")状态信息不符合, session断裂!");
					DefaultMessage mhaoMessag = new DefaultMessage(mhaoDefaultMessage.getMsgName()+1, mhaoDefaultMessage.getMsgSender(), "", mhaoDefaultMessage.getMsgSeq(), session);
					try {
						session.write(mhaoMessag);// 这里用户可能不在线
						logger.info(mhaoDefaultMessage);
					} catch (Exception e) {
						logger.info(e.getMessage());
					}
					closeSession(session);
					return ;
				}
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
		}
		execute(session, msg);
	}
}
