package com.example.rabbitMQ.service;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.rabbitMQ.model.Message;

@Service
public class RabbitMQSender {
	private static final Logger logger = LoggerFactory.getLogger(RabbitMQSender.class);
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	private CountDownLatch latch = new CountDownLatch(1);
	
	@Value("${com.example.rabbitMQ.exchange}")
	private String exchange;
	
	@Value("${com.example.rabbitMQ.routingkey}")
	private String routingkey;
	
	String topic = "sendMsg_rabbitMQ";
	
	public void send(Message message) {		
		amqpTemplate.convertAndSend(exchange, routingkey, message);
		latch.countDown();
		logger.info("Sended msg = " + message);		
		System.out.println("Sended message..." + message);	
	}
	
    public CountDownLatch getLatch() {
       return latch;
    }
	
	
}
