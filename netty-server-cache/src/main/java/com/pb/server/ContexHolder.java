package com.pb.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContexHolder {
	private static ApplicationContext context = new ClassPathXmlApplicationContext(
			"spring.xml");

	public static Object getBean(String name) {
		return context.getBean(name);
	}

	public static <T> T getBean(Class<T> c) {
		return context.getBean(c);
	}
}
