package com.lami.tarsier.message;

import com.lami.tarsier.consumer.Consumer;
import com.lami.tarsier.consumer.UserCache;
import com.lami.tarsier.core.impl.MessageBuffer;
import com.lami.tarsier.bootstrap.SocketServer;
import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

public class MessageBusiness extends MessageBuffer {
	
	static Logger logger = Logger.getLogger(MessageBusiness.class);
	
	private UserCache userCache;
	protected SocketServer _socketServer = null;
	
	
	public void setLoader(SocketServer socketServer) {
		_socketServer = socketServer;
	}
	public void setUserCache(UserCache userCache) {
		this.userCache = userCache;
	}

	private void default_return(IoSession session, DefaultMessage msg){
		DefaultMessage defaultMessage = (DefaultMessage) msg;
		if (defaultMessage.getMsgName() % 2 > 0) {
			DefaultMessage mm = new DefaultMessage(defaultMessage.getMsgName() + 1,
					defaultMessage.getMsgSender() , defaultMessage.getVersion(),"0", defaultMessage.getMsgSeq());
			logger.info(mm.toString());
			session.write(mm);
		}
	}

	/**
	 * 消息处理
	 */
	@Override
	public void message(IoSession iosession, Message output) throws Exception {
		try {
			DefaultMessage defaultMessage = (DefaultMessage) output;
			if (defaultMessage == null) {
				return;
			}	
			BaseBusiness bb = null;
			defaultMessage.setSessionId(iosession.getId());
			//判断userId是否正确
			Consumer mu = userCache.get(iosession.getId());
			if(mu!=null&&mu.getUid()!=null&&mu.getUid().intValue()>0&& defaultMessage.getMsgSender()>0){
				if(mu.getUid().intValue()!= defaultMessage.getMsgSender()){
					logger.warn("msg sender different!");
					defaultMessage.setMsgSender(mu.getUid().intValue());
				}
			}
			switch (defaultMessage.getMsgName()) {
			case DefaultMessage.NAME_CREATE_SESSION:
				bb = new CreateSession(userCache, _socketServer);
				break;
			default:
				logger.error("message unknow:"+ defaultMessage.toString());
				break;
			}
			
			if(bb!=null)
				bb.doExecute(iosession, defaultMessage);
			else
				default_return(iosession, defaultMessage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
