package com.etoak.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.etoak.bean.Email;

@Service
public class EmailService {
	
	@Autowired
	SimpleMailMessage mailMessage;
	
	@Autowired
	JavaMailSenderImpl mailSender;
	
	@Autowired
	ThreadPoolTaskExecutor executor;
	
	public void send(Email email) {
		mailMessage.setTo(email.getReceiver());
		mailMessage.setSubject(email.getSubject());
		mailMessage.setText(email.getContent());
		
		executor.execute(()->{
			mailSender.send(mailMessage);
			System.out.println("邮件下发结束");
		});
	}
}
