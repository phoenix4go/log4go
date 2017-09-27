package com.phoenix4go.log4go.jms;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import com.phoenix4go.log4go.properties.PropertiesLog4Go;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsTemplate;

public class SenderLogFactory {

	private PropertiesLog4Go propertiesLog4Go;

	SenderLogFactory(){
		propertiesLog4Go = PropertiesLog4Go.builder()
										   .loadProperties("log4go.properties")
										   .build();
	}

	public static SenderLog getSenderLog() {
		SenderLogFactory senderLogFactory = new SenderLogFactory();
		JmsTemplate jmsTemplate = new JmsTemplate(senderLogFactory.connectionFactory());
		jmsTemplate.setDefaultDestination(senderLogFactory.destination());
		return SenderLogDelegate.createInstance(jmsTemplate);
	}

	private ConnectionFactory connectionFactory() {
		return new ActiveMQConnectionFactory(propertiesLog4Go.getUser(),
											 propertiesLog4Go.getPassword(),
											 propertiesLog4Go.getBrokerLogURL());
	}
	
	private Destination destination() {
		return new ActiveMQQueue(propertiesLog4Go.getQueueName());
	}

}
