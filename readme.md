# Log4Go Project

Centralize logging via JMS

### Dependency Maven
[Artifactory](https://maven.apache.org/)
```xml
<dependency>
	<groupId>com.phoenix4go</groupId>
	<artifactId>log4go</artifactId>
	<version>X.X.X-SNAPSHOT</version>
</dependency>
```	

### Quick Start
```java

// Declarando 
static LoggerTokio logger = LoggerTokioFactory.getLogger(MyClass.class, "application", "division");

// Simple
logger.info("Quick Start Logtokio...!");

// With document
logger.info("<field-one>My Document</field-one>", "Quick Start Logtokio...!"); 

// Format
logger.info("Quick Start Logtokio... %s - %s", "arguments-1", "arguments-2");

// With document and Format
logger.info("<field-one>My Document</field-one>", "Quick Start Logtokio... %s - %s", "arguments-1", "arguments-2");
```
### System Properties
```
ACTIVEMQ_URL_LOG=failover:(tcp://localhost:61616,tcp://srvjava003d:61616)?randomize=false
ACTIVEMQ_QUEUE_LOG=TOKIO.Q.LOG.PUT
ACTIVE_MQ_USER_LOG=user1
ACTIVE_MQ_PASSWORD_LOG=pass2017
```

##### Desenv 
Run As > Run Configurations... > Arguments > VM Arguments:
```
 -DACTIVEMQ_URL_LOG=failover:(tcp://localhost:61616)?randomize=false -DACTIVEMQ_QUEUE_LOG=TOKIO.Q.LOG.PUT   
 -DACTIVE_MQ_USER_LOG=user1 -DACTIVE_MQ_PASSWORD_LOG=pass2017
```

##### Server Application
JBoss/Wildfly
```xml
<system-properties>
	<property name="ACTIVEMQ_URL_LOG" value="failover:(tcp://localhost:61616)?randomize=false"/>
	<property name="ACTIVEMQ_QUEUE_LOG" value="TOKIO.Q.LOG.PUT"/>
	<property name="ACTIVE_MQ_USER_LOG" value="user1"/>
	<property name="ACTIVE_MQ_PASSWORD_LOG" value="pass2017"/>  
</system-properties>
```

Obs...:   
> Caso a property `ACTIVEMQ_URL_LOG` não estiver especificada no contexto, a biblioteca pegará a `ACTIVEMQ_URL`.   
> Property `ACTIVEMQ_QUEUE_LOG` não estando especificada pegará a String `"TOKIO.Q.LOG.PUT"`.   
> Property `ACTIVE_MQ_USER_LOG` não estando especificada pegará a property `ACTIVE_MQ_USER`.   
> Property `ACTIVE_MQ_PASSWORD_LOG` não estando especificada pegará a property `ACTIVE_MQ_PASSWORD`.   
```java
ACTIVEMQ_URL_LOG == null ? ACTIVEMQ_URL : ACTIVEMQ_URL_LOG   
ACTIVEMQ_QUEUE_LOG == null ? "TOKIO.Q.LOG.PUT" : ACTIVEMQ_QUEUE_LOG   
ACTIVE_MQ_USER_LOG == null ? ACTIVE_MQ_USER : ACTIVE_MQ_USER_LOG   
ACTIVE_MQ_PASSWORD_LOG == null ? ACTIVE_MQ_PASSWORD : ACTIVE_MQ_PASSWORD_LOG   
```

[Participe do desenvolvimento](https://trello.com/b/1wS1FI0u/logtokio-project)

