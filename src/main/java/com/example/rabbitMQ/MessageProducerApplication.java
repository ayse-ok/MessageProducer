package com.example.rabbitMQ;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.rabbitMQ.model.Message;
import com.example.rabbitMQ.service.RabbitMQSender;

@SpringBootApplication
public class MessageProducerApplication{ //implements CommandLineRunner
	@Autowired
	RabbitMQSender producer;

	public static void main(String[] args) {
		SpringApplication.run(MessageProducerApplication.class, args);
	}

	// multiple message sending 
//	@Override
//	public void run(String... args) throws Exception {
//		//using 1st routing
//		String content = "Message 1....";
//		Integer id = 1;
//		
//		// send to RabbitMQ
//		producer.send(new Message(content, id));
//		producer.getLatch().await(10000, TimeUnit.MILLISECONDS);
//		
//		//using 2nd routing
//		content = "Message 2....";
//		id++;
//		
//		// send to RabbitMQ
//		producer.send(new Message(content, id));
//		producer.getLatch().await(10000, TimeUnit.MILLISECONDS);
//		
//		//using 3rd routing
//		content = "Message 3....";
//		id++;
//		
//		// send to RabbitMQ
//		producer.send(new Message(content, id));
//		producer.getLatch().await(10000, TimeUnit.MILLISECONDS);
//		
//	}
	
	

}
