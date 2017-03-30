![Image of Nested](images/EE.png) 

EJB(Enterprise java bean) is a specification provided by Sun Microsystems to develop secured, robust, scalable, distributed applications.Vendors uses this specification to implement an infrastructure(EJB Container) in which components(enterprise bean is a server-side component that encapsulates the business logic of an application) can be deployed and provides set of services on that components.
1. Transaction management 
2. Security authorization
3. Manages life cycle of Resource object e.g EntityManager,ejb instances thus developer needs not to worry about when to create/delete ejb objects
4. logging, load balancing, persistence mechanism, exception handling
5. Interceptor
6. Timers
7. Messaging
8. web services support
8. Namming service : register unique lookup name in the JNDI namespace so that client can access bean

Some vendors are glashfish,Jboss,Weblogic,TomEE,WebSphere (IBM),WebLogic (BEA) etc

### Benefits of Enterprise Beans ###
1. Since it is deployed on EJB container.EJB container provides above system-level services.developer can concentrate only develop business logic code
2. Since client developer does not have to code the routines that implement business rules or access databases. As a result, the clients are thinner, a benefit that is particularly important for clients that run on small devices.
3. because enterprise beans are portable components, the application assembler can build new applications from existing beans

### When to Use Enterprise Beans ### 
You should consider using enterprise beans if your application has any of the following requirements.

1. The application must be scalable. To accommodate a growing number of users, you may need to distribute an application’s components across multiple machines. Not only can the enterprise beans of an application run on different machines, but also their location will remain transparent to the clients.

2. Transactions must ensure data integrity. Enterprise beans support transactions, the mechanisms that manage the concurrent access of shared objects.

3. The application will have a variety of clients. With only a few lines of code, remote clients can easily locate enterprise beans. These clients can be thin, various, and numerous.


### EJB >3.0 vs EJB < 3.0  Version ###
The development process of creating EJB between before 3.0 and after 3.0 are different.We need to understand difference between >3.0 and <3.0


EJB < 3.0 | EJB >=3.0 
------------ | -------------
Configuration using programmatic and declarative(using confiquration file) |  Configruation using Annotaton ,and option delcarative way that that facilate quick development
To create enterprise bean need to implement REMOTE,HOME,Local interface | Simple POJO based Bean
Entity bean is used for persistence.it could be either CMP(COntainer managed persistence) or BMP(Bean managed persistence).BMP is programmatic | JPA-java persistence api(replace on entity bean) is for persistence
Access beans through Only JNDI lookup  | Access beans either dependency injection using @EJB annotation or JNDI lookup. 


### Types of Enterprise Beans ###

EJB 3.0 defines two types of enterprise beans.

1. Session Bean
2. Message driven bean

There was another bean before 3.0 called Entity Bean

### Session Bean ###
Session means short duration of time execute something.On otherhand it is not used for persistence.It encapsulates actual business logic like user authentication,credit card validation,shopping card etc

### Client of Session Bean###

### Steps to create a bean of before 2.0 style ###
1. Create bean class with all of the business method implementation
2. Create two interface for the bean
	1. Component Interface either local or remote : 
	2. Home Interface


3. Create an XML deployment descriptor that tells the server what your bean is and how it should be managed.File name must be ejb-jar.xml under META-INF
4. Register the bean into JNDI . file name may be sun-ejb-jar.xml or EJB container specific file glassfish-ejb-jar.xml(glassfish),weblogic-ejb-jar.xml(Weblogic server) under META-INF
5. Deploy the bean into server using tool provided by vendor

### Example of Hello World EJB ###

1. create maven java project by following command

	`mvn archetype:generate -DgroupId=com.javaaround -DartifactId=EJB -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false` 

	Add javaee (include ejb jar here) dependency at pom.xml

	```xml
	<dependency>
	    <groupId>javax</groupId>
	    <artifactId>javaee-api</artifactId>
	    <version>7.0</version>
	</dependency>
	```

2. Create HelloWorldBean.java at com/javaaround/ejb



	```java
	package com.javaaround.ejb;
	import javax.ejb.*;
	import java.util.*;

	public class HelloWorldBean implements SessionBean{
		public String hello(){
		     return "Hello World EJB";
		}
		public void ejbCreate() {
			System.out.println( "Ejb create" );
		} 
		public void ejbRemove() {
			System.out.println( "Ejb remove" );
		}
		public void ejbActivate() {
			System.out.println( "Ejb Activate" );
		}
		public void ejbPassivate() {
			System.out.println( "Ejb Passivate" );
		}
		public void setSessionContext(SessionContext sc){
			System.out.println( "SessionContext" );
		}
	}
	```

	Explanation :

	1. Bean must be extends SessionBean. it have four methods(ejbRemove(),ejbActivate(),ejbPassivate(),setSessionContext()) that must be implement
	2. Must have ejbCreate() method . It is EJB rule but not extends from SessionBean
	3. hello() method actual business logic

3. Create HelloWorldRemote.java at com/javaaround/ejb
	
	```java
	package com.javaaround.ejb;
	import javax.ejb.*;
	import java.rmi.*;
	import java.util.*;
	public interface HelloWorldRemote extends EJBObject{
	   public String hello() throws RemoteException;
	}
	```

	Explanation : Component Interface(EJB Object): Component Interface is define the business methods that is visible to client.An EJB client never invokes an instance of enterprise bean class directly. A method invocation is intercepted by EJB COntainer that provides the services to delegate the method execution to enterprise bean at the right time.hence it is passive component managed by EJB Container.

	There are two types of Component Interface

	1. Remote Component : if ejb client are outside of the container then remote componet is used.you must extends `javax.ejb.EJBObject` to define remote component and access through the RMI-IIOP protocol.
	2. Local Component : if ejb client are inside of the container then remote componet is used.you must extends `javax.ejb.EJBLocalObject` to define Local component

	Here define your own busineess methods only since it is an interfaces


	Create HelloWorldHome.java at com/javaaround/ejb

	```java
	package com.javaaround.ejb;

	import javax.ejb.*;
	import java.rmi.*;
	public interface HelloWorldHome extends EJBHome{
	   public HelloWorldRemote create() throws RemoteException,CreateException;
	}
	```

	Explanation : Home Interface(EJB Home): Home Interface is used by the client to ask for a reference  to the component interface. You think,Home as a kind of factory(home) that makes and distributes bean reference to the client

	Home Interfaces contains life cycle methods of `create,find or remove`


4. Create ejb-jar.xml at src/main/resources/META-INF

	```xml
	<?xml version="1.0"?>
	<!DOCTYPE ejb-jar PUBLIC
	"-//Sun Microsystems, Inc.//DTD Enterprise JavaBeans 2.0//EN"
	"http://java.sun.com/dtd/ejb-jar_2_0.dtd">

	<ejb-jar>
		<enterprise-beans>
			<session>
				<ejb-name>helloWorldBean</ejb-name>
				<home>com.javaaround.ejb.HelloWorldHome</home> 
				<remote>com.javaaround.ejb.HelloWorldRemote</remote>
				<ejb-class>com.javaaround.ejb.HelloWorldBean</ejb-class>
				<session-type>Stateless</session-type>
				<transaction-type>Container</transaction-type>
			</session>
		</enterprise-beans>
	</ejb-jar>
	```

	Explanation : 

		1. `<ejb-name>` : specify your ejb name that is used register with jndi 
		2. `<home>` : specify your home interface
		3. `<remote>` : specify your remote interface
		4. `<ejb-class>` : specify your bean class
		5. `<session-type>` : specify bean type either Stateless,Statefull,Singleton . discuss later

5. Create sun-ejb-jar.xml at src/main/resources/META-INF

	```xml
	<!DOCTYPE sun-ejb-jar PUBLIC "-//Sun Microsystems, Inc.//DTD Application Server 9.0 EJB 2.0//EN" "http://www.sun.com/software/appserver/dtds/sun-ejb-jar_2_0-0.dtd">
	<sun-ejb-jar>
	    <display-name>First Module</display-name>
	    <enterprise-beans>
	        <ejb>
	            <ejb-name>helloWorldBean</ejb-name>
	            <jndi-name>helloworld</jndi-name>
	        </ejb>
	    </enterprise-beans>
	</sun-ejb-jar>
	```

	Explanation : 

		1. `<ejb-name>` : specify your ejb name that is configure ejb-jar.xml for 
		register with jndi.if not specify then default name assume .See server log/console
		2. `<jndi-name>` : specify your jndi name

6. package the app by following command

	`mvn clean package`

	upload ejb/target/EJB-1.0-SNAPSHOT.jar by glassfish administrator UI 

	or by command

	asadmin deploy "F:\java_tutorial\ejb/target/EJB-1.0-SNAPSHOT.jar"

	for redeploy use

	asadmin redeploy "F:\java_tutorial\ejb/target/EJB-1.0-SNAPSHOT.jar" 

### Desktop Client App ###

1. create maven java project by following command

	`mvn archetype:generate -DgroupId=com.javaaround -DartifactId=EJBClient -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false` 
2. Copy HelloWorldRemote.java &&  HelloWorldHome.java to com/javaaround/ejb

3. Update App.java

	```java
	package com.javaaround;
	import java.util.Properties;
	import javax.naming.InitialContext;
	import javax.naming.NamingException;
	import com.javaaround.ejb.HelloWorldHome;
	import com.javaaround.ejb.HelloWorldRemote;
	public class App 
	{
	    public static void main( String[] args )
	    {
	        Properties properties = new Properties();
			properties.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");  
			properties.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");  
			properties.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");  
			properties.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");  
			properties.setProperty("org.omg.CORBA.ORBInitialPort", "3700");  
			  
			try
			{
			    InitialContext ctx = new InitialContext( properties );
			    HelloWorldHome home = (HelloWorldHome)ctx.lookup("java:global/EJB-1.0-SNAPSHOT/helloWorldBean!com.javaaround.ejb.HelloWorldHome");
			    HelloWorldRemote object = home.create();
			    System.out.println(object.hello());
			}
			catch (Exception e )
			{
			    e.printStackTrace();
			}
			
	    }
	}

	```

	Explanation : since it is remote(outside server) client . so we need to basic environment like ip etc

 
4. Update AppTest.java

	```java
	package com.javaaround;

	import org.junit.Test;;


	/**
	 * Unit test for simple App.
	 */
	public class AppTest {
	   @Test
	   public void AppTest( ){
	        App.main(null);
	   }
	}
	```
5. Update junit version && add gf-client dependency(provider specific) at pom.xml
	
	```xml
	<version>4.8.1</version>
	```

	```java
	<dependency>
        <groupId>org.glassfish.main.appclient</groupId>
        <artifactId>gf-client</artifactId>
        <version>4.1</version>
    </dependency>
	```
6. Run app by following command

   `mvn clean package`

### Above Hello World > EJB 3.0 style ###
It is recommend to use version 3.0 style way EJB devlopement since it is annotation based.it is introduced from jdk 5.0

EJB 3.0  specification says that The requirement for Home interfaces has been eliminated. Session beans are no longer required to have home interfaces. A client may acquire a reference to a session bean directly

1. Update HelloWorldRemote.java
	```java
	package com.javaaround.ejb;
	import java.rmi.*;
	import java.util.*;
	import javax.ejb.Remote;
	@Remote
	public interface HelloWorldRemote{
	   public String hello();
	}	
	``` 

	Explanation : Component Interface(EJB Object) :
	1. Remote Component is defined by @Remote
	2. Local Component is defined by @Local


2. Update HelloWorldBean.java
	```java
	package com.javaaround.ejb;
	import javax.ejb.Stateless;
	@Stateless
	public class HelloWorldBean implements HelloWorldRemote{
		@Override
		public String hello(){
		     return "Hello World EJB";
		}
	}	
	``` 
3.  Update ejb-jar.xml
	This file is not required. it is just needed because the Maven compiles with error like it could not find a ejb-jar.xml.	

	```java
		<?xml version="1.0" encoding="UTF-8"?>  
	<ejb-jar  
	 xmlns="http://java.sun.com/xml/ns/javaee"  
	 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
	 xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/ejb-jar_3_0.xsd"  
	 version="3.0">  
	</ejb-jar>
	```
4. package the app by following command

	`mvn clean package`

### Desktop Client App ###
1. Update HelloWorldRemote.java &&  HelloWorldHome.java with the above content
2. Update App.java

	```java
	InitialContext ctx = new InitialContext( properties );
    HelloWorldRemote object = (HelloWorldRemote)ctx.lookup("java:global/EJB-1.0-SNAPSHOT/HelloWorldBean");
    System.out.println(object.hello());
	``` 
3. package the app by following command

	`mvn clean package`	
	
### Types of Session Bean ###
There are 3 types of session bean.
1. Stateless Session Bean : 


