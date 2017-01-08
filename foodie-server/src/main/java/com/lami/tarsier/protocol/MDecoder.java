package com.lami.tarsier.protocol;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import com.lami.tarsier.core.impl.DecoderBuffer;
import com.lami.tarsier.message.Container;
import com.lami.tarsier.message.DefaultMessage;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;


public class MDecoder implements DecoderBuffer {
	static Logger logger = Logger.getLogger ( MDecoder.class ) ;
	
	public Container decoder ( IoSession iosession, IoBuffer iobuffer ) throws Exception {
		Container object = new Container () ;
		while ( true ) {
			try {
				if ( ( iobuffer.limit () - iobuffer.position () ) <= 12 ) {
	    			byte[] left_tmp = new byte[ iobuffer.limit () - iobuffer.position () ] ;
					iobuffer.get ( left_tmp ) ;
					break ;
				}
		    	
				byte[] buf = new byte[12];
				iobuffer.get(buf);
		    	CommHead msg = CommHead.createMessage ( buf ) ;
	    		if ( !msg.isMsgBandage () || ( iobuffer.limit () - iobuffer.position () ) < msg.getMsgLength () ) {  			
	    			byte[] left_tmp = new byte[ iobuffer.limit () - iobuffer.position () ] ;
					iobuffer.get ( left_tmp ) ; 
					break ;
	    		}
	    		byte[] mbuf = new byte[msg.getMsgLength ()];
	    		iobuffer.get(mbuf);
				DefaultMessage smDefaultMessage = new DefaultMessage().decodeMsg(mbuf);
				InetSocketAddress socketAddress = ( InetSocketAddress ) iosession.getRemoteAddress() ;
				String ip = socketAddress.getAddress().getHostAddress();
				smDefaultMessage.setAddress ( ip ) ;
				object.add (smDefaultMessage) ;
				if ( ( iobuffer.limit () - iobuffer.position () ) <= 0 ) {
					break ;
				}
			} catch ( Exception exc ) {
				logger.error ( exc.getMessage (), exc ) ;
    			byte[] left_tmp = new byte[ iobuffer.limit () - iobuffer.position () ] ;
				iobuffer.get ( left_tmp ) ; 
				break ;				
			}
		}
    	return object ;
	}
	
	public static final String addressToString ( SocketAddress address ) {
		 // getHostName() that method will perform a reverse hostname lookup. 
		//  So the performance of that method call depends on the performance of the network/technology stack between the JVM and the domain name server for the target host.
		InetSocketAddress socketAddress = ( InetSocketAddress ) address ;
		return new StringBuilder ().append ( socketAddress.getHostName() ). 
			append ( ":" ).append ( socketAddress.getPort () 
		).toString () ;
	}
	
}
