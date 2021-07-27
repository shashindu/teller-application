package com.ncs.xmlprocessor.configs;


import javax.jms.ConnectionFactory;

import org.apache.camel.component.jms.JmsComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.JmsTransactionManager;

@Configuration 
public class JmsConfig {

	
	@Bean
	public JmsTransactionManager creatJmsTransactionManager(final ConnectionFactory connectionFactory) {

		JmsTransactionManager jmsTransactionManager = new JmsTransactionManager();
		jmsTransactionManager.setConnectionFactory(connectionFactory);
		return jmsTransactionManager;

	}

	@Bean
	public JmsComponent creatJmsComponent(final ConnectionFactory connectionFactory,
			JmsTransactionManager jmsTransactionManager,
			@Value("${max.concurrent.consumers}") final int maxConcurrentConsumers) {

		JmsComponent jmsComponent = JmsComponent.jmsComponentTransacted(connectionFactory, jmsTransactionManager);
		jmsComponent.setMaxConcurrentConsumers(maxConcurrentConsumers);
		return jmsComponent;

	}

}
