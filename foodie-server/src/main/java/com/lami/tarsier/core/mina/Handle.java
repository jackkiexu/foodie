/*
 *  Author: zhaijl 
 *  Url: http://218.1.73.212/down.php
 *  Country: China
 *  
 * ====================================================================
 *
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * ====================================================================
 *
 * This software based apache mina 2.0 and spring 3.0
 *
 */
package com.lami.tarsier.core.mina;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import com.lami.tarsier.core.conf.Configure;
import com.lami.tarsier.core.impl.MessageBuffer;
import com.lami.tarsier.message.Container;

import com.lami.tarsier.message.Message;
import com.lami.tarsier.message.DefaultMessage;
import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class Handle extends IoHandlerAdapter {
	static Logger logger = Logger.getLogger ( Handle.class ) ;
	private Configure _configure ;
	
	public void setConfigure ( Configure configure ) {
		this._configure = configure ;
	}	

    public void exceptionCaught ( IoSession session, Throwable cause ) 
    	throws Exception {    	
    	try{
    		logger.warn ( "Handle caught exception :"+new StringBuffer ( new Long ( session.getId () ).toString () ).
        		append ( cause.getMessage() ).toString () 
        		) ;
    	}catch (Exception e){
    		e.printStackTrace();
    	}
    	//logger.warn ( cause.getMessage (), cause ) ;   
    }

    public void messageReceived ( IoSession session, Object message ) 
    	throws Exception {     	    	
    	Container container = ( Container )message ;
    	for ( Message<?> output : container ) {
    		MessageBuffer.getInstance(_configure.getMsgsufix()).message ( session, output ) ;
    	}
    }   
    
    public void sessionOpened ( IoSession session ) throws Exception {
    	session.getConfig ().setMinReadBufferSize ( _configure.getMinbufsize () ) ;
    	session.getConfig ().setMaxReadBufferSize ( _configure.getMaxbufsize () ) ;
	}    

    public void sessionCreated ( IoSession session ) throws Exception {
    	logger.info ( new StringBuffer ( "sessionCreated (IP:"+session.getRemoteAddress()+" and sessionId :"+session.getId()+")" ).toString()) ;
    	DefaultMessage mm = new DefaultMessage(DefaultMessage.NAME_CREATE_SESSION,-1,"sessionCreated");
    	mm.setAddress(addressToString ( session.getRemoteAddress ()));
    	MessageBuffer.getInstance (_configure.getMsgsufix ()).message ( session, mm ) ;
    }

    public void sessionClosed ( IoSession session ) throws Exception {
     	logger.info ( new StringBuffer ( "sessionClosed (IP:"+session.getRemoteAddress()+" and sessionId :"+session.getId()+")" ).toString()) ;
		DefaultMessage mm = new DefaultMessage(DefaultMessage.NAME_LOGOUT,-1,"sessionClosed");
    	mm.setAddress(addressToString ( session.getRemoteAddress ()));    	
    	MessageBuffer.getInstance (_configure.getMsgsufix ()).message ( session, mm ) ;
    }
    public static final String addressToString ( SocketAddress address ) {
		InetSocketAddress socketAddress = ( InetSocketAddress ) address ;
		return new StringBuilder ().append ( socketAddress.getAddress().getHostAddress() ).
			append ( ":" ).append ( socketAddress.getPort () 
		).toString () ;
	}
}

