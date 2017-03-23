![Image of Nested](images/EE.png) 

EJB(Enterprise java bean) is a specification provided by Sun Microsystems to develop secured, robust, scalable, distributed applications.Vendors uses this specification to implement an infrastructure(EJB Container) in which components(enterprise bean is a server-side component that encapsulates the business logic of an application) can be deployed and provides set of services on that components.
1. Transaction management 
2. Security authorization
3. Manages life cycle of Resource object e.g EntityManager,ejb instances thus developer needs not to worry about when to create/delete ejb objects
4. logging, load balancing, persistence mechanism, exception handling
5. Interceptor
6. Timers
7. Messaging
8. web services support.

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
Entity bean is used for persistence | JPA-java persistence api(replace on entity bean) is for persistence
Access beans through Only JNDI lookup  | Access beans through dependency injection using @EJB annotation or JNDI lookup. 