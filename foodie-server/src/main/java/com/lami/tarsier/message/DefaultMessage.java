package com.lami.tarsier.message;

import java.io.UnsupportedEncodingException;

import com.lami.tarsier.core.util.Seq;
import com.lami.tarsier.core.util.TypeUtil;
import com.lami.tarsier.protocol.CommHead;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;


public class DefaultMessage extends Message<DefaultMessage> {

	private static final Logger logger = Logger.getLogger(DefaultMessage.class);
	
	public static final Integer STATUS_SUCCESS=1;
	/** parameters error */
	public static final Integer STATUS_FAILURE=-1;
	/** re login */
	public static final Integer STATUS_RELOGIN=-2;
	/** auth failure */
	public static final Integer STATUS_SIGN_ERROR=-3;
	
	/** create session */
	public static final int NAME_CREATE_SESSION = 0;
	/** logout */
	public static final int NAME_LOGOUT = 1;

	public static final int NAME_LOGOUT_ACK = 2;
	/** connect */
	public static final int NAME_LINK = 3;
	public static final int NAME_LINK_ACK = 4;
	/** heartbeat */
	public static final int NAME_ENQUIRE = 5;
	public static final int NAME_ENQUIRE_ACK = 6;

	// msg type
	private int msgName;
	// userId
	private int msgSender;
	// seqId
	private int msgSeq;

	private int version;

	private int messageLen;

	private long sessionId;

	private String message;

	private CommHead msgHead ;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}

	private String address ;
	private Integer port ;

	public DefaultMessage(){}

	public DefaultMessage(int msgName, int sender, String info, int seqId, IoSession ioSession) {
		this.msgName = msgName;
		this.msgSender = sender;
		this.version = 0;
		this.msgSeq = seqId;
		this.message = info;
		this.sessionId = ioSession.getId();
		try {
			this.messageLen = info.getBytes("GB2312").length;
		} catch (UnsupportedEncodingException e) {
			logger.info(e.getMessage());
		}
		this.setMsgHead(new CommHead(size()));
	}
	public DefaultMessage(int msgName, int sender, String info) {
		this.msgName = msgName;
		this.msgSender = sender;
		this.version = 0;
		this.msgSeq = Seq.getSequences();
		this.message = info;
		try {
			this.messageLen = info.getBytes("GB2312").length;
		} catch (UnsupportedEncodingException e) {
			logger.info(e.getMessage());
		}
		this.setMsgHead(new CommHead(size()));
	}
	public DefaultMessage(int msgName, int sender, int version, String info, int seqId) {
		this.msgName = msgName;
		this.msgSender = sender;
		this.version = version;
		this.msgSeq = seqId;
		this.message = info;
		try {
			this.messageLen = info.getBytes("GB2312").length;
		} catch (UnsupportedEncodingException e) {
			logger.info(e.getMessage());
		}
		this.setMsgHead(new CommHead(size()));
	}

	public void setMsgName(int msgName) {
		this.msgName = msgName;
	}

	public void setMsgSeq(int msgSeq) {
		this.msgSeq = msgSeq;
	}

	public void setMsgHead(CommHead msgHead) {
		this.msgHead = msgHead;
	}
	
	public int getMsgName() {
		return msgName;
	}

	public int getMsgSeq() {
		return msgSeq;
	}
	
	public CommHead getMsgHead() {
		return this.msgHead;
	}	

	public int getMsgSender() {
		return msgSender;
	}

	public void setMsgSender(int msgSender) {
		this.msgSender = msgSender;
	}

	public int getMessageLen() {
		return messageLen;
	}

	public void setMessageLen(int messageLen) {
		this.messageLen = messageLen;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int size() {
		int tmpSize = 0;
		tmpSize += TypeUtil.SIZE_OF_INT; // msgName
		tmpSize += TypeUtil.SIZE_OF_INT; // msgSender
		tmpSize += TypeUtil.SIZE_OF_INT; // msgSeq
		tmpSize += TypeUtil.SIZE_OF_INT; // version
		tmpSize += TypeUtil.SIZE_OF_INT; // messageLen
		tmpSize += this.getMessageLen();
		return tmpSize;
	}
	
	public Integer toType () {
		return this.msgName ;
	}

	public byte[] encodeMsg() {
		byte[] buf = new byte[this.size()+getMsgHead().size()];
		int j = 0;
		
		System.arraycopy(getMsgHead().getMsgBandage(), 0, buf, j, 8) ;
		j += 8 ;				

		System.arraycopy ( TypeUtil.int2bytes(getMsgHead().getMsgLength()), 0, buf, j, TypeUtil.SIZE_OF_INT ) ;
		j += TypeUtil.SIZE_OF_INT ;	
		
		System.arraycopy(TypeUtil.int2bytes(this.getMsgName()), 0, buf, j, TypeUtil.SIZE_OF_INT) ;
		j += TypeUtil.SIZE_OF_INT ;		

		System.arraycopy ( TypeUtil.int2bytes(this.getMsgSender()), 0, buf, j, TypeUtil.SIZE_OF_INT ) ;
		j += TypeUtil.SIZE_OF_INT ;		

		System.arraycopy(TypeUtil.int2bytes(this.getMsgSeq()), 0, buf, j, TypeUtil.SIZE_OF_INT) ;
		j += TypeUtil.SIZE_OF_INT ;		
		
		System.arraycopy ( TypeUtil.int2bytes(this.getVersion()), 0, buf, j, TypeUtil.SIZE_OF_INT ) ;
		j += TypeUtil.SIZE_OF_INT ;	
		
		System.arraycopy(TypeUtil.int2bytes(this.getMessageLen()), 0, buf, j, TypeUtil.SIZE_OF_INT) ;
		j += TypeUtil.SIZE_OF_INT ;	
		
		try {
			System.arraycopy(this.getMessage().getBytes("GB2312"), 0, buf, j,
					this.getMessageLen());
			j += this.getMessageLen();
		} catch (Exception e) {
		}
		return buf;
	}

	@Override
	public DefaultMessage decodeMsg(byte[] buf) {
		DefaultMessage msg = new DefaultMessage();
		int j = 0;

		byte[] tBuf = new byte[TypeUtil.SIZE_OF_INT];
		System.arraycopy ( buf, j, tBuf, 0, TypeUtil.SIZE_OF_INT ) ;
		msg.setMsgName(TypeUtil.bytes2int(tBuf));
		j += TypeUtil.SIZE_OF_INT ;

		tBuf = new byte[TypeUtil.SIZE_OF_INT];
		System.arraycopy ( buf, j, tBuf, 0, TypeUtil.SIZE_OF_INT ) ;
		msg.setMsgSender(TypeUtil.bytes2int(tBuf));
		j += TypeUtil.SIZE_OF_INT ;

		tBuf = new byte[TypeUtil.SIZE_OF_INT];
		System.arraycopy ( buf, j, tBuf, 0, TypeUtil.SIZE_OF_INT ) ;
		msg.setMsgSeq(TypeUtil.bytes2int(tBuf));
		j += TypeUtil.SIZE_OF_INT ;

		tBuf = new byte[TypeUtil.SIZE_OF_INT];
		System.arraycopy ( buf, j, tBuf, 0, TypeUtil.SIZE_OF_INT ) ;
		msg.setVersion(TypeUtil.bytes2int(tBuf));
		j += TypeUtil.SIZE_OF_INT ;

		tBuf = new byte[TypeUtil.SIZE_OF_INT];
		System.arraycopy ( buf, j, tBuf, 0, TypeUtil.SIZE_OF_INT ) ;
		msg.setMessageLen(TypeUtil.bytes2int(tBuf));
		j += TypeUtil.SIZE_OF_INT ;

		if(msg.getMessageLen()>0&&msg.getMessageLen()<102400){
			tBuf = new byte[msg.getMessageLen()];
			System.arraycopy(buf, j, tBuf, 0, msg.getMessageLen());
			try {
				msg.setMessage(new String(tBuf, "GB2312"));
			} catch (Exception exc) {
				logger.info(exc.getMessage());
				return null;
			}
		}else{
			msg.setMessage("");
		}
		return msg;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("["+this.sessionId+"]");
		if(this.getAddress()!=null)
			sb.append("[" + this.getAddress () + "]," );
		sb.append("MsgName=" + this.getMsgName());
		sb.append(",MsgSender=" + this.getMsgSender());
		sb.append(",MsgSeq=" + this.getMsgSeq());
		sb.append(",Version=" + this.getVersion());
		sb.append(",MessageLen=" + this.getMessageLen());
		sb.append(",Message=" + this.getMessage());
		return sb.toString();
	}

}
