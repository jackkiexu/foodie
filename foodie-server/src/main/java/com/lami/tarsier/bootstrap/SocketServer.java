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
package com.lami.tarsier.bootstrap;

import java.net.InetSocketAddress;

import com.lami.tarsier.core.codec.CodecFactory;
import com.lami.tarsier.core.conf.Configure;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;


public class SocketServer {

	private SocketAcceptor _acceptor ;
	private Configure _configure ;
	private IoHandler _hander ;
	private IoFilter _filter ;	
	
	public void setConfigure ( Configure configure ) {
		this._configure = configure ;
	}
	
	public Configure getConfigure () {
		return this._configure ;
	}

	public void setAcceptor ( SocketAcceptor acceptor ) {
		this._acceptor = acceptor ;
	}

	public void setHander ( IoHandler hander ) {
		this._hander = hander ;
	}

	public void setFilter ( IoFilter filter ) {
		this._filter = filter ;
	}
	
	public void logger () {}
	
	public IoSession getSession ( Long key ) throws Exception {
		return _acceptor.getManagedSessions ().get ( key ) ;
	}
	
	public WriteFuture send ( Long key, Object object ) throws Exception {
		IoSession session = _acceptor.getManagedSessions ().get ( key ) ;
		if ( session != null ) {
			return session.write ( object ) ;
		}
		return null ;
	}	
	
	public void start () throws Exception {				
		_acceptor.setReuseAddress( true );
		DefaultIoFilterChainBuilder chain = _acceptor.getFilterChain() ;
		chain.clear();
		chain.addLast ( "head", _filter ) ;
		chain.addLast ( "codec", new ProtocolCodecFilter ( new CodecFactory( this ) ) ) ;
		_acceptor.setHandler ( _hander ) ;
		_acceptor.bind ( new InetSocketAddress ( _configure.getPort () ) ) ;
		_acceptor.setReuseAddress(true);
		_acceptor.getSessionConfig ().setReadBufferSize ( _configure.getReadbufsize () ) ; 
		_acceptor.getSessionConfig ().setReceiveBufferSize ( _configure.getReceivebufsize () ) ; 
		//_acceptor.getSessionConfig().setTcpNoDelay(true);
		
		
	}
	
	public void dispose () throws Exception {		
		if ( !_acceptor.isDisposed () ) {
			_acceptor.unbind () ;
			_acceptor.dispose () ;
		}
	}
}