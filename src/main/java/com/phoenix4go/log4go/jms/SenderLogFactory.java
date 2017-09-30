package com.phoenix4go.log4go.jms;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import com.phoenix4go.log4go.properties.ApplicationProperties;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsTemplate;

public class SenderLogFactory {

	private ApplicationProperties applicationProperties;

	SenderLogFactory(){
		applicationProperties = ApplicationProperties.builder()
										   .loadProperties()
										   .build();
	}

	public static SenderLog getSenderLog() {
		SenderLogFactory senderLogFactory = new SenderLogFactory();
		JmsTemplate jmsTemplate = new JmsTemplate(senderLogFactory.connectionFactory());
		jmsTemplate.setDefaultDestination(senderLogFactory.destination());
		return SenderLogDelegate.createInstance(jmsTemplate);
	}

	private ConnectionFactory connectionFactory() {
		return new ActiveMQConnectionFactory(applicationProperties.getUser(),
											 applicationProperties.getPassword(),
											 applicationProperties.getBrokerURL());
	}
	
	private Destination destination() {
		return new ActiveMQQueue(applicationProperties.getQueueName());
	}

}
