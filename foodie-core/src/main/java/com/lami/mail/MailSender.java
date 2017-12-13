package com.lami.mail;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MailSender {
	private final static Logger logger=Logger.getLogger(MailSender.class);
	private MailSender(){
	}
	private static class InstanceHolder{
		private static MailSender instance=new MailSender();
	}
	public static  MailSender  getInstance(){
		return InstanceHolder.instance;
	}
	public void send(String title,List<String> toList,String content,List<String> fileList) {
		Mail mail=new Mail("smtp.exmail.qq.com","services@mmhub.com","mmhub客服","services@mmhub.com","mmhub213",toList,title,content);
		if(fileList!=null)
		for(String str:fileList){
			mail.addAttachfile(str);
		}
		Map<String, String> map = mail.send();
		logger.info(map);
	}
	
	public static void main(String[] args) {
		List<String> toList=new ArrayList<String>();
		toList.add("1803@qq.com");
		new MailSender().send("xxxxxx", toList, "xxxx", null);
	}
}
