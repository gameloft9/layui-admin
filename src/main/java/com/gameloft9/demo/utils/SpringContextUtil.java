package com.gameloft9.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * spring context工具类
 * @author gameloft9
 */
@Slf4j
public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext context;

	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		log.info("Initializing application context util.");
		SpringContextUtil.context = context;
	}

	public static ApplicationContext getApplicationContext() {
		return context;
	}

	public static Object getBean(String name) {
		return context.getBean(name);
	}

	public static <T> T getBean(Class<T> clz) {
		return context.getBean(clz);
	}

}
