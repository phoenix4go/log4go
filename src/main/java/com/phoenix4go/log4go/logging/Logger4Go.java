/* 
 * Copyright (C) 2017 @diegolirio
 * 
 * Este programa é uma api de log, utilizando JMS para envio das informações.
 * Para mais detalhes acesse. https://gist.github.com/diegolirio/afd07a9701e28760f0828e82121b57a8
 * 
 */
package com.phoenix4go.log4go.logging;

public interface Logger4Go {

	boolean withSlf4j = false;
	
	void info(java.lang.String message);
	
	void info(java.lang.String document, java.lang.String message);
	
	void info(java.lang.String format, java.lang.Object... args);
	
	void info(java.lang.String document, java.lang.String format, java.lang.Object... args);
	
	void error(java.lang.String message);

	void error(java.lang.String document, java.lang.String message);
	
	void error(java.lang.String format, java.lang.Object... args);
	
	void error(java.lang.String document, java.lang.String format, java.lang.Object... args);

	void warn(java.lang.String message);

	void warn(java.lang.String document, java.lang.String message);
	
	void warn(java.lang.String format, java.lang.Object... args);		
	
	void warn(java.lang.String document, java.lang.String format, java.lang.Object... args);	
	
	void debug(java.lang.String message);

	void debug(java.lang.String document, java.lang.String message);
	
	void debug(java.lang.String format, java.lang.Object... args);		
	
	void debug(java.lang.String document, java.lang.String format, java.lang.Object... args);		
	
}
