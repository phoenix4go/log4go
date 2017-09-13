package com.phoenix4go.logtokio.jms;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.util.StringUtils;

public abstract class SenderLogFactory {
	
	private static String brokerLogURL = System.getProperty("ACTIVEMQ_URL_LOG"); //"failover:(tcp://srvjava003d:61616)?randomize=false"; //System.getProperty("ACTIVEMQ_URL_LOG");  | srvjava003d | localhost
	private static String brokerMainURL = System.getProperty("ACTIVEMQ_URL");
	private static String user = System.getProperty("ACTIVE_MQ_USER"); 
	private static String password = System.getProperty("ACTIVE_MQ_PASSWORD");
	private static String queueName = System.getProperty("ACTIVEMQ_QUEUE_LOG"); //"TOKIO.Q.LOG.PUT"; //System.getProperty("ACTIVEMQ_QUEUE_LOG");
	
	SenderLogFactory(){}
	
	private static String getBrokerURL() {
		return StringUtils.isEmpty(brokerLogURL) ? brokerMainURL : brokerLogURL;
	}
	
	public static SenderLog getSenderLog() {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
		jmsTemplate.setDefaultDestination(destination());
		return SenderLogDelegate.createInstance(jmsTemplate);
	}
	
	private static ConnectionFactory connectionFactory() {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(SenderLogFactory.user, SenderLogFactory.password,  SenderLogFactory.getBrokerURL());
		return connectionFactory;
	}	
	
	private static Destination destination() {
		Queue queue = new ActiveMQQueue(queueName == null ? "TOKIO.Q.LOG.PUT" : queueName);
		return queue;
	}

}
