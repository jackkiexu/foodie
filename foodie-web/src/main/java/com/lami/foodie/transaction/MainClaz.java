package com.lami.foodie.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import roseindia.net.bean.DependencyBean;

public class MainClaz {

	private static final Logger logger = LoggerFactory.getLogger(MainClaz.class);
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/springContent.xml");
		TransactionTemplateTest transactionTemplateTest = (TransactionTemplateTest) context.getBean("transactionTemplateTest");
		transactionTemplateTest.testPlatformTransactionManager();

	}
}
