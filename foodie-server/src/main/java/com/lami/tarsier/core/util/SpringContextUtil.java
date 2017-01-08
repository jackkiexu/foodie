package com.lami.tarsier.core.util;

import org.springframework.context.ApplicationContext;

public class SpringContextUtil {
	private static ApplicationContext context;

	public static void setApplicationContext(ApplicationContext acx) {
		context = acx;
	}

	public static ApplicationContext getApplicationContext() {
		return context;
	}

	public static Object getBean(String name) {
		return context.getBean(name);
	}
}
