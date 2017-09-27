package com.phoenix4go.log4go.properties;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLog4Go {

    @Getter
    private String brokerLogURL;
    @Getter
    private String user;
    @Getter
    private String password;
    @Getter
    private String queueName;

    public static PropertiesLog4Go builder() {
        return new PropertiesLog4Go();
    }

    public PropertiesLog4Go loadProperties(String fileName) {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = getClass().getClassLoader().getResourceAsStream(fileName);
            if(input == null)
                throw new RuntimeException(String.format("%s file not found!",fileName));
            prop.load(input);
            this.brokerLogURL = prop.getProperty("log4go.activemq.broker-url");
            if(this.brokerLogURL == null)
                throw new RuntimeException("BrokerURL not found! Add to property log4go.activemq.broker-url");
            this.user = prop.getProperty("log4go.activemq.user");
            this.password = prop.getProperty("log4go.activemq.password");
            this.queueName = prop.getProperty("log4go.activemq.queue-name");
            if(this.queueName == null) this.queueName = "LOG4GO.Q.LOG";
            return this;
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

    public PropertiesLog4Go build() {
        return this;
    }
}
