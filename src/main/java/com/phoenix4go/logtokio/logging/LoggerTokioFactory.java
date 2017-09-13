package com.phoenix4go.logtokio.logging;

public abstract class LoggerTokioFactory {
	
	public static LoggerTokio getLogger(Class<?> clazz, String sistema, String divisao) {
		try {
			LoggerTokio loggerTokio = LoggerTokioImpl.createInstance(clazz, sistema, divisao);
			return loggerTokio;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e); 
		}
	}
}
