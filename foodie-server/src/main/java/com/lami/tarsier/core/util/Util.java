package com.lami.tarsier.core.util;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger; 
import java.io.UnsupportedEncodingException; 

public class Util {
	static Logger logger = Logger.getLogger(Util.class);
	public static String getExtend(String filename) {
		if (filename == null || filename.length() <= 0)
			return "";
		int pos = filename.lastIndexOf(".");
		if (pos < 0)
			return "";
		return filename.substring(pos);
	}

	public static String array2String(String[] array) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length - 1; i++) {
			sb.append(array[i]).append(",");
		}

		if (array.length > 0) {
			sb.append(array[array.length - 1]);
		}
		return sb.toString();
	}

	public static String getFilename(String ctx) {
		if (ctx == null)
			return null;

		int first = ctx.lastIndexOf("/");
		if (first < 0) {
			return null;
		}
		return ctx.substring(first);
	}

	public static java.util.Date formatDate(String date, String format) {
		try {
			SimpleDateFormat dateformat = new SimpleDateFormat(format);
			return dateformat.parse(date);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return new java.util.Date();
	}

	public static String formatDate(java.util.Date date) {
		DateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
		return format.format(date);
	}

	public static String formatDate(java.util.Date date, String ft) {
		DateFormat format = new SimpleDateFormat(ft);
		return format.format(date);
	}

	public static String getMD5Str(String str) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException exc) {
			exc.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException exc) {
			exc.printStackTrace();
			return null;
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return md5StrBuff.toString();
	}

	public static String toStringHex(String s) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(
						i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			s = new String(baKeyword, "utf-8");// UTF-16le:Not
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}

	

}
