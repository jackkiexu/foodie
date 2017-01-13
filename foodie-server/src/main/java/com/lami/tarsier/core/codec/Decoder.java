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
package com.lami.tarsier.core.codec;

import com.lami.tarsier.core.impl.DecoderBuffer;
import com.lami.tarsier.bootstrap.SocketServer;
import com.lami.tarsier.core.util.SpringContextUtil;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;


public class Decoder implements ProtocolDecoder {
	static Logger logger = Logger.getLogger ( Decoder.class ) ;
	private SocketServer _socketServer;

	public Decoder ( SocketServer socketServer) {
		_socketServer = socketServer;
	}
	
    public void decode ( IoSession iosession, IoBuffer iobuffer, ProtocolDecoderOutput protocoldecoderoutput ) 
		throws Exception {
    	DecoderBuffer object = ( DecoderBuffer ) SpringContextUtil.getBean(_socketServer.getConfigure().getDecoder()) ;
    	protocoldecoderoutput.write ( object.decoder ( iosession, iobuffer ) ) ;
    }	

    public void finishDecode ( IoSession iosession, ProtocolDecoderOutput protocoldecoderoutput )
		throws Exception {
    	
    }
    
    public void dispose ( IoSession iosession ) throws Exception {}  
}