package com.lami.tarsier.core.util;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.*;



public class SpringContextLoader {

	static Logger logger = Logger.getLogger ( SpringContextLoader.class ) ;
	
	private static SpringContextLoader _this = new SpringContextLoader();
	
	public static SpringContextLoader getInstance() {
		return _this;
	}

	public void init(String...configs){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(configs);
		SpringContextUtil.setApplicationContext(applicationContext);
	}
	
	public void destrory() {
		((FileSystemXmlApplicationContext)(SpringContextUtil.getApplicationContext())).close();
		((FileSystemXmlApplicationContext)(SpringContextUtil.getApplicationContext())).destroy();
	}
	
}
