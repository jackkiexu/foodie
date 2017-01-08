package com.lami.tarsier.protocol;


import com.lami.tarsier.core.impl.EncoderBuffer;
import com.lami.tarsier.message.DefaultMessage;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;


public class MEncoder implements EncoderBuffer {
	static Logger logger = Logger.getLogger ( MEncoder.class ) ;

	public IoBuffer encoder ( IoSession iosession, Object object ) throws Exception {
		DefaultMessage defaultMessage = (DefaultMessage)object ;
		if ( defaultMessage == null ) return IoBuffer.allocate ( 0 ) ;
		   
		IoBuffer buffer = IoBuffer.allocate ( defaultMessage.size ()) ;
		buffer.setAutoExpand ( true ) ;
		buffer.put ( defaultMessage.encodeMsg() ) ;
		buffer.flip () ;  
		return buffer ;
	}
}
