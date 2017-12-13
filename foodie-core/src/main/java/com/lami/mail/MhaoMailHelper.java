package com.lami.mail;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MhaoMailHelper {
	
	private static ThreadPoolExecutor mhaoMailSendPool = null;
	private MhaoMailHelper(){
		mhaoMailSendPool = new ThreadPoolExecutor(1, 1, 1, TimeUnit.HOURS, new LinkedBlockingQueue<Runnable>(), new IOSMsgThreadFactory("IOSMsgThreadPool"));
	}
	private static class inner{
		private static MhaoMailHelper instance = new MhaoMailHelper();
	}
	public static MhaoMailHelper getnstance(){
		return inner.instance;
	}
	
	public void sendMail(String title,List<String> toList,String content){
		mhaoMailSendPool.execute(new SendMailThread(title,toList,content));
	}
	
	class IOSMsgThreadFactory implements ThreadFactory{
		private Integer count = 0;
		private String nameThread;
		public IOSMsgThreadFactory(String nameThread) {
			this.nameThread = nameThread;
		}
		@Override
		public Thread newThread(Runnable r) {
			count++;
			if(count>=Integer.MAX_VALUE)count=0;
			return new Thread(r, nameThread + " " + count);
		}
	}
	
	public static void main(String[] args) {
		List<String> toList = new ArrayList<String>();
		toList.add("1695220577@qq.com");
		MhaoMailHelper.getnstance().sendMail("xxx",toList, "toList");
	}
}

