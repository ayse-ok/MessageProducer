package com.example.rabbitMQ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.rabbitMQ.model.Message;
import com.example.rabbitMQ.service.RabbitMQSender;

@RestController
@RequestMapping(value="/sendMsg-rabbitMQ")
public class RabbitMQWebController {
	@Autowired
	RabbitMQSender rabbitMQSender;
	
	@GetMapping(value="/producer")
	public String producer(@RequestParam("icerik") String icerik, @RequestParam("messageId") Integer id) {
		Message message = new Message();
		message.setContent(icerik);
		message.setMessageId(id);
		
		rabbitMQSender.send(message);
		return "Mesajınız başarıyla RabbitMQ ya gönderildi..";
	}

}
