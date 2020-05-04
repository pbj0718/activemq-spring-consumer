package com.etoak.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestConsumer {
	public static void main(String args[]) {
		new ClassPathXmlApplicationContext("spring.xml");
	}
}
