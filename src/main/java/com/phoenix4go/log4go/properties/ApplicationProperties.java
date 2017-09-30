package com.phoenix4go.log4go.properties;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationProperties {

    @Getter
    private String brokerURL;
    @Getter
    private String user;
    @Getter
    private String password;
    @Getter
    private String queueName;

    private boolean loaded = false;
    private static ApplicationProperties applicationProperties;

    private void setBrokerURL(String brokerURL) {
        this.brokerURL = brokerURL;
        if(this.brokerURL == null)
            throw new RuntimeException("BrokerURL not found! Add to property log4go.activemq.broker-url");
    }

    private void setQueueName(String queueName) {
        this.queueName = queueName;
        if(this.queueName == null)
            this.queueName = "LOG4GO.Q.LOG";
    }

    public static ApplicationProperties builder() {
        if(applicationProperties == null)
            applicationProperties = new ApplicationProperties();
        return applicationProperties;
    }

    public ApplicationProperties loadProperties() {
        if(this.loaded) return this;
        final String fileName = "application.properties";
        InputStream input = null;
        try {
            input = getClass().getClassLoader().getResourceAsStream(fileName);
            if(input == null)
                throw new RuntimeException(String.format("%s file not found!",fileName));
            this.setProperties(input);
            this.loaded = true;
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

    private void setProperties(InputStream input) throws IOException {
        Properties prop = new Properties();
        prop.load(input);
        this.setBrokerURL(prop.getProperty("log4go.activemq.broker-url"));
        this.user = prop.getProperty("log4go.activemq.user");
        this.password = prop.getProperty("log4go.activemq.password");
        this.setQueueName(prop.getProperty("log4go.activemq.queue-name"));
    }

    public ApplicationProperties build() {
        return this;
    }
}
