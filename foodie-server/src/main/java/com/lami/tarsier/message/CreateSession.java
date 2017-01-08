package com.lami.tarsier.message;

import java.net.InetSocketAddress;


import com.lami.tarsier.consumer.UserCache;
import com.lami.tarsier.bootstrap.SocketServer;
import org.apache.mina.core.session.IoSession;

public class CreateSession extends BaseBusiness {

	public CreateSession(UserCache userCache, SocketServer _socketServer
			) {
	}
	
	@Override
	public boolean checkUserBeanStatus() {
		return false;
	}
	
	/**
	 * app发送心跳，保持app的在线状态
	 * 
	 * @param session
	 * @param msg
	 */
	public void execute(IoSession session, DefaultMessage msg) {
		InetSocketAddress socketAddress = ( InetSocketAddress ) session.getRemoteAddress() ;
		String ip = socketAddress.getAddress().getHostAddress();
		userCache.addIp(ip);
		int num = userCache.getIpNum(ip);
		if(num<20){
			userCache.addSession(session.getId(), null);
		}else{
			logger.info("session close ip : " + ip);
			session.close(true);
		}
	}
}
