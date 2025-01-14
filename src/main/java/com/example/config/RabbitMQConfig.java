package com.example.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.consumer.payment.service.PaymentConsumer;

@Configuration
public class RabbitMQConfig {

	/*
	 * private static final String EXCHANGE_NAME = "OrderExchange"; private static
	 * final String ROUTING_KEY = "OrderRoutingKey"; private static final String
	 * QUEUE_NAME = "PaymentQueue";
	 */
	
	@Bean
    public TopicExchange exchange() {
        return new TopicExchange("OrderExchange");
    }

    @Bean
    public Queue queue() {
        return new Queue("OrderQueue");
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("OrderRoutingKey");
    }

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames("order.queue");
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	public MessageListenerAdapter listenerAdapter(PaymentConsumer paymentConsumer) {
		return new MessageListenerAdapter(paymentConsumer, "processPayment");
	}

}
