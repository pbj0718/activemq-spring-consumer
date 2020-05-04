package com.etoak.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.etoak.bean.Email;
import com.etoak.service.EmailService;

public class EmailListener implements MessageListener {
	
	@Autowired
	EmailService emailService;
	
	@Override
	public void onMessage(Message message) {
		if(message instanceof TextMessage) {
			TextMessage text = (TextMessage)message;
			String jsonMsg;
			try {
				jsonMsg = text.getText().toString();
				System.out.println("receive msg - " + jsonMsg );
				// 把json消息转换为email对象
				Email email = JSONObject.parseObject(jsonMsg,Email.class);
				emailService.send(email);
				// 签收消息
				text.acknowledge();
			} catch (JMSException e) {
				e.printStackTrace();
			}
			
			
		}
	}

}
