package com.phoenix4go.log4go;

import com.phoenix4go.log4go.logging.Logger4Go;
import com.phoenix4go.log4go.logging.Logger4GoFactory;

/**
 * @diegoLirio App!
 *
 */
public class App {

	private static Logger4Go logger = Logger4GoFactory.getLogger(App.class, "log4go", "lib");

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
