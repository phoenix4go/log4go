package com.phoenix4go.log4go.logging;

public abstract class Logger4GoFactory {
	
	public static Logger4Go getLogger(Class<?> clazz, String sistema, String divisao) {
		try {
			Logger4Go loggerTokio = Logger4GoImpl.createInstance(clazz, sistema, divisao);
			return loggerTokio;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e); 
		}
	}
}
