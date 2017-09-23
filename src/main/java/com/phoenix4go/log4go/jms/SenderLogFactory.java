package com.phoenix4go.log4go.jms;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsTemplate;

public class SenderLogFactory {

	private String brokerLogURL, user, password, queueName;

	SenderLogFactory(){
		this.loadProperties();
	}

	public static SenderLog getSenderLog() {
		SenderLogFactory senderLogFactory = new SenderLogFactory();
		JmsTemplate jmsTemplate = new JmsTemplate(senderLogFactory.connectionFactory());
		jmsTemplate.setDefaultDestination(senderLogFactory.destination());
		return SenderLogDelegate.createInstance(jmsTemplate);
	}
	
	private ConnectionFactory connectionFactory() {
		return new ActiveMQConnectionFactory(this.user, this.password,  this.brokerLogURL);
	}
	
	private Destination destination() {
		return new ActiveMQQueue(this.queueName);
	}

	private void loadProperties() {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			String log4goFile = "log4go.properties";
			input = getClass().getClassLoader().getResourceAsStream(log4goFile);
			if(input == null)
				throw new RuntimeException("log4go.properties not found!");
			prop.load(input);
			this.brokerLogURL = prop.getProperty("log4go.activemq.broker-url");
			if(this.brokerLogURL == null)
				throw new RuntimeException("BrokerURL not found...!");
			this.user = prop.getProperty("log4go.activemq.user");
			this.password = prop.getProperty("log4go.activemq.password");
			this.queueName = prop.getProperty("log4go.activemq.queue-name");
			if(this.queueName == null) this.queueName = "LOG4GO.Q.LOG";
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if(input != null)
				try {
					input.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
		}

	}

}
