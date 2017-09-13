package com.phoenix4go.logtokio;

import com.phoenix4go.logtokio.logging.LoggerTokio;
import com.phoenix4go.logtokio.logging.LoggerTokioFactory;

/**
 * @diegoLirio App!
 *
 */
public class App {

	private static LoggerTokio logger = LoggerTokioFactory.getLogger(App.class, "logtokio", "lib");

	App() {
		logger.info("Hello World!");
	}

	private void print() {
		logger.info("print!");
		logger.info("{\"id\":1}", "print!");
	}

	public static void main(String[] args) {
		//System.setProperty("ACTIVEMQ_URL_LOG", "failover:(tcp://localhost:61616)");
		//System.setProperty("ACTIVEMQ_QUEUE_LOG", "TOKIO.Q.LOG.PUT");
		App app = new App();
		app.print();
	}
}
