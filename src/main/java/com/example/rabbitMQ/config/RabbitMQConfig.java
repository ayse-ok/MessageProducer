package com.example.rabbitMQ.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.example.rabbitMQ" })
public class RabbitMQConfig{	
	@Value("${com.example.rabbitMQ.queue}")
	String queueName;

	@Value("${com.example.rabbitMQ.exchange}")
	String exchange;

	@Value("${com.example.rabbitMQ.routingkey}") 
	private String routingkey;
	
	private String dlRoutingkey = "deadLetter";
	private String dlExchange = "deadLetterExchange";
	private String dlQueue = "deadLetter.queue";
	
	
	// -------deadletter olmadan queune tanımlama-------
//	@Bean
//	Queue queue() {
//		return new Queue(queueName, false);
//	}
	
	// -------deadletter kullanmak icin queune argumentlerle tanımlanıyor------
	@Bean
	Queue queue() {
		return QueueBuilder.durable(queueName).withArgument("x-dead-letter-exchange", dlExchange)
				.withArgument("x-dead-letter-routing-key", dlRoutingkey).build();
	}

	@Bean
	DirectExchange exchange() {
		return new DirectExchange(exchange);
	}
	
	@Bean	
	Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingkey);
	}
	
	
	// -------- gönderilemeyen mesajları deadLetterExchange ile deadLetterQueune e ekleyebiliriz ---
	
	@Bean
	Queue dlQueue() {
		return QueueBuilder.durable(dlQueue).build();
	}
	
	@Bean
	DirectExchange deadLetterExchange() {
		return new DirectExchange(dlExchange);
	}
	
	@Bean
	Binding DLQbinding() {
		return BindingBuilder.bind(dlQueue()).to(deadLetterExchange()).with(dlRoutingkey);
	}
			

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}
	
	
	
}
