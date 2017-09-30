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
### Create `application.properties` in `src/main/resources` folder 
```
log4go.activemq.broker-url=failover:(tcp://localhost:61616)?randomize=false
log4go.activemq.user=my_user
log4go.activemq.password=*************
log4go.activemq.queue-name=MY_QUEUE_NAME_LOG # Opicional: Caso nao coloque nome da fila será LOG4GO.Q.LOG 
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

### Overview
![Logtokio Image](https://raw.githubusercontent.com/diegolirio/diegolirio/master/images/logtokio-image.png)

[Participe do desenvolvimento](https://trello.com/b/1wS1FI0u/logtokio-project)

