package com.lami.tarsier.protocol;

import com.lami.tarsier.core.util.TypeUtil;

import java.util.Arrays;

public class CommHead implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
    private byte[] msgBandage;
    private int msgLength;

    public static final byte[] msgBandageFlag ={(byte)0x00,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0x00};
    public static final byte[] msgClearedFlag ={(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};

    
    public CommHead(){
    	this.msgBandage = msgBandageFlag;
    }
    public CommHead(int msgLength) {
    	this.msgBandage = msgBandageFlag;
    	this.msgLength = msgLength;
	}

	public void setMsgBandage(byte[] msgBandage) {
		this.msgBandage = msgBandage;
	}

	public void setMsgLength(int msgLength) {
		this.msgLength = msgLength;
	}

	public byte[] getMsgBandage() {
		return msgBandage;
	}

	public int getMsgLength() {
		return msgLength;
	}

	public int size() {
		int tmpSize = 0;
		tmpSize += 8; // msgBandage
		tmpSize += TypeUtil.SIZE_OF_INT; // msgLength

		return tmpSize;
	}

	public byte[] getBytes() {
		byte[] buf = new byte[this.size()];
		int j = 0;

		System.arraycopy ( msgBandage, 0, buf, j, 8 ) ;
		j += 8 ;				

		System.arraycopy ( TypeUtil.int2bytes(this.getMsgLength()), 0, buf, j, TypeUtil.SIZE_OF_INT ) ;
		j += TypeUtil.SIZE_OF_INT ;				

		return buf;
	}

	public boolean isMsgBandage() {
		if (this.msgBandage.length == 8
				&& this.msgBandage[0] == CommHead.msgBandageFlag[0]
				&& this.msgBandage[1] == CommHead.msgBandageFlag[1]
				&& this.msgBandage[2] == CommHead.msgBandageFlag[2]
				&& this.msgBandage[3] == CommHead.msgBandageFlag[3]
				&& this.msgBandage[4] == CommHead.msgBandageFlag[4]
				&& this.msgBandage[5] == CommHead.msgBandageFlag[5]
				&& this.msgBandage[6] == CommHead.msgBandageFlag[6]
				&& this.msgBandage[7] == CommHead.msgBandageFlag[7]) {
			return true ;
		}
		return false;
	}
	
	public static CommHead createMessage(byte[] buf) {
		//byte[] buf = new byte[len];
		CommHead msg = new CommHead();
		int j = 0;

		byte[] tBuf = new byte[8];
		System.arraycopy ( buf, j, tBuf, 0, 8 ) ;
		j += 8 ;		
		msg.setMsgBandage(tBuf);

		tBuf = new byte[TypeUtil.SIZE_OF_INT];
		System.arraycopy ( buf, j, tBuf, 0, TypeUtil.SIZE_OF_INT ) ;
		j += TypeUtil.SIZE_OF_INT ;		
		msg.setMsgLength(TypeUtil.bytes2int(tBuf));
		return msg;
	}
	@Override
	public String toString() {
		return "CommHead [msgBandage=" + Arrays.toString(msgBandage)
				+ ", msgLength=" + msgLength + "]";
	}
	
}
