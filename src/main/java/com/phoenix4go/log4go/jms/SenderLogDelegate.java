package com.phoenix4go.log4go.jms;

import org.springframework.jms.core.JmsTemplate;

final class SenderLogDelegate implements SenderLog {

	//private static final Logger logger = LoggerFactory.getLogger(SenderLog.class);
	
	private JmsTemplate jmsTemplate;
	
	SenderLogDelegate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	public void sendMessage(final String message) {
		//logger.info(String.format("<<< Queue: %s >>>", brokerURL));
		//logger.info("*************************************************");
		System.out.println("<<< Send JMS("+message+") >>>");
		//logger.info("*************************************************");
		//logger.info("");
		this.jmsTemplate.convertAndSend(message);
	}

	public static SenderLog createInstance(JmsTemplate jmsTemplate) {
		return new SenderLogDelegate(jmsTemplate);
	}	

}
