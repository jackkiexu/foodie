package com.lami.foodie.transaction.test1;

import com.lami.foodie.transaction.TransactionTemplateTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MainClaz {

	private static final Logger logger = LoggerFactory.getLogger(MainClaz.class);
	public static void main(String[] args) {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/springContent.xml");
			StudentJDBCTemplate studentJDBCTemplate = (StudentJDBCTemplate) context.getBean("studentJDBCTemplate");
			studentJDBCTemplate.create("xjk", 28, 7, 1989);

			List<StudentMarks>  studentMarksList = studentJDBCTemplate.listStudents();

			logger.info(studentMarksList + "");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
