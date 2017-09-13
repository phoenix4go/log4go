/* 
 * Copyright (C) 2017 @diegolirio
 * 
 * Este programa é uma api de log, utilizando JMS para envio das informações.
 * 
 */
package com.phoenix4go.log4go.logging;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoenix4go.log4go.dto.Log;
import com.phoenix4go.log4go.dto.LogType;
import com.phoenix4go.log4go.jms.SenderLog;
import com.phoenix4go.log4go.jms.SenderLogFactory;

class Logger4GoImpl implements Logger4Go {
	
	private String divisao;
	private String sistema;
	private Class<?> clazz;
	
	private static Logger log4j;
	private static SenderLog senderLog;
	private static ObjectMapper objectMapper;
	
	Logger4GoImpl(Class<?> clazz, String sistema, String divisao) {
		this.sistema = sistema;
		this.divisao = divisao;
		this.clazz = clazz;
	}

	public static Logger4GoImpl createInstance(Class<?> clazz, String sistema, String divisao) throws InstantiationException, IllegalAccessException {
		if(withSlf4j) 
			log4j = LoggerFactory.getLogger(clazz);
		setupObjectMapper();		
		senderLog = SenderLogFactory.getSenderLog();
		Logger4GoImpl loggerTokioImpl = new Logger4GoImpl(clazz, sistema, divisao);
		return loggerTokioImpl;
	}
	
	private static void setupObjectMapper() {
		objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS"));
	}

	public void info(String message) {
		if(withSlf4j) 
			log4j.info(message); // TODO: refatorar para 1 lugar soh...
		this.send(LogType.INFO, null, message);
	}
	
	public void info(String document, String message) {
		if(withSlf4j) 
			log4j.info(message);
		this.send(LogType.INFO, document, message);
	}	

	public void info(String format, Object... args) {
		if(withSlf4j) 
			log4j.info(String.format(format, args));
		this.send(LogType.INFO, null, String.format(format, args));
	}

	public void info(String document, String format, Object... args) {
		if(withSlf4j) 
			log4j.info(String.format(format, args));
		this.send(LogType.INFO, document, String.format(format, args));
	}	
	
	public void error(String message) {
		if(withSlf4j) 
			log4j.error(message);
		this.send(LogType.ERROR, null, message);
	}
	
	public void error(String document, String message) {
		if(withSlf4j) 
			log4j.error(message);
		this.send(LogType.ERROR, document, message);
	}	

	public void error(String format, Object... args) {
		if(withSlf4j) 
			log4j.error(format, args);
		this.send(LogType.ERROR, null, String.format(format, args));
	}

	public void error(String document, String format, Object... args) {
		if(withSlf4j) 
			log4j.error(format, args);
		this.send(LogType.ERROR, document, String.format(format, args));
	}	
	
	public void warn(String message) {
		if(withSlf4j) 
			log4j.warn(message);
		this.send(LogType.WARNING, null, message);
	}

	public void warn(String document, String message) {
		if(withSlf4j) 
			log4j.warn(message);
		this.send(LogType.WARNING, document, message);
	}	
	
	public void warn(String format, Object... args) {
		if(withSlf4j) 
			log4j.warn(format, args);
		this.send(LogType.WARNING, null, String.format(format, args));
	}	

	public void warn(String document, String format, Object... args) {
		if(withSlf4j) 
			log4j.warn(format, args);
		this.send(LogType.WARNING, document, String.format(format, args));
	}		
	
	public void debug(String message) {
		if(withSlf4j) 
			log4j.debug(message);
		this.send(LogType.DEBUG, null, message);
	}

	public void debug(String document, String message) {
		if(withSlf4j) 
			log4j.debug(message);
		this.send(LogType.DEBUG, document, message);
	}	
	
	public void debug(String format, Object... args) {
		if(withSlf4j) 
			log4j.debug(format, args);
		this.send(LogType.DEBUG, null, String.format(format, args));
	}	

	public void debug(String document, String format, Object... args) {
		if(withSlf4j) 
			log4j.debug(format, args);
		this.send(LogType.DEBUG, document, String.format(format, args));
	}		
	
	private void sendLogger(LogType logType, String log, String document, String sistema, String divisao) {
		try {
			Log logObj = Log.builder()
							   .dataHora(Calendar.getInstance())
							   .logType(logType)
							   .log(log)
							   .sistema(sistema)
							   .divisao(divisao)
							   .clazzName(clazz.getName())
							   .document(document)
							   .build();
			String json = objectMapper.writeValueAsString(logObj);
			senderLog.sendMessage(json); 
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	private void send(LogType logType, String document, String message) {
		this.sendLogger(logType, message, document, this.sistema, this.divisao);
	}

}
