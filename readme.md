# Log4Go Project

Centralize logging via JMS

### Dependency Maven
[Artifactory](http://srvcem002a/artifactory/webapp/search/artifact?24&q=logtokio)
```xml
<dependency>
	<groupId>com.phoenix4go</groupId>
	<artifactId>log4go</artifactId>
	<version>X.X.X-SNAPSHOT</version>
</dependency>
```		

### Quick Start
```java
static Logger4go logger = Logger4goFactory.getLogger(MyClass.class, "application", "section");
logger.info("Quick Start Log4go...!"); // simple
logger.info("<field-one>My Document</field-one>", "Quick Start Logtokio...!"); // with document
```
### System Properties
```
ACTIVEMQ_URL_LOG=failover:(tcp://localhost:61616,tcp://srvjava003d:61616)?randomize=false
ACTIVEMQ_QUEUE_LOG=TOKIO.Q.LOG.PUT
```

##### Desenv 
Run As > Run Configurations... > Arguments > VM Arguments:
```
 -DACTIVEMQ_URL_LOG=failover:(tcp://localhost:61616)?randomize=false -DACTIVEMQ_QUEUE_LOG=TOKIO.Q.LOG.PUT
```

##### Server Application
JBoss/Wildfly
```xml
<property name="ACTIVEMQ_URL_LOG" value="failover:(tcp://localhost:61616)?randomize=false"/>
<property name="ACTIVEMQ_QUEUE_LOG" value="TOKIO.Q.LOG.PUT"/>
```

Obs...:   
```
Caso a property ACTIVEMQ_URL_LOG não estiver especificada no contexto, a biblioteca pegará a ACTIVEMQ_URL.
Para a property ACTIVEMQ_QUEUE_LOG, não estando especificada pegará TOKIO.Q.LOG.PUT.
```
```java
ACTIVEMQ_URL_LOG == null ? ACTIVEMQ_URL : ACTIVEMQ_URL_LOG
ACTIVEMQ_QUEUE_LOG == null ? "TOKIO.Q.LOG.PUT" : ACTIVEMQ_QUEUE_LOG
```


