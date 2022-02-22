package com.example.rabbitMQ.service;

import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.rabbitMQ.model.Message;

@Service
public class RabbitMQSender {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	private CountDownLatch latch = new CountDownLatch(1);
	
	@Value("${com.example.rabbitMQ.exchange}")
	private String exchange;
	
	@Value("${com.example.rabbitMQ.routingkey}")
	private String routingkey;
	
	String topic = "sendMsg_rabbitMQ";
	
	public void send(Message message) {
		System.out.println("Sending message..." + message);	
		amqpTemplate.convertAndSend(exchange, routingkey, message);
		latch.countDown();
		System.out.println("Sended message : " + message);
	}
	
    public CountDownLatch getLatch() {
       return latch;
    }
	
	
}
