package com.lami.mail;

import org.apache.log4j.Logger;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SendMailThread implements Runnable {
	
	private static final Logger logger = Logger.getLogger(MhaoMailHelper.class);
	
	private String subject;
	private String content;
	private List<String> toList;
	
	public SendMailThread(String subject,List<String> toList,String content) {
		this.subject = subject;
		this.content = content;
		this.toList = toList;
	}
	
	@Override
	public void run() {
		send(subject, toList, content, new ArrayList<String>());
	}

	/**
     * 发送邮件
     * @param title  邮件主题
     * @param content  邮件内容
     * 		toList 收件人
     * 		fileList 附件 可为空
     */
	private boolean send(String subject,List<String> toList,String content,List<String> fileList) {
		Mail mail=new Mail("smtp.exmail.qq.com","xujiankang@alphaun.com","mhao","xujiankang@alphaun.com","xjk19891102inusa",toList,subject,content);
		for(String str:fileList){
			mail.addAttachfile(str);
		}
		Map<String, String> map = mail.send();
		logger.info(map);
		return true;
	}
}
