# T1M - Tier One Data Monitoring prototype

This is a starter project based on the following technologies;

* AngularJS
* Twitter BootStrap
* Spring Boot
* Hibernate
* QueryDSL

It includes a basic working end-to-end use case with the ability to create and save People objects. The AngularJS client
code uses REST calls to talk to the Spring Boot Controller classes. These pass the request down into the Service layer
where Hibernate+JPA is used to load and save the Person data from the SQL Server database. Additionally the QueryDSL
library is also integrated with the Gradle build process to provide nice dynamic queries.

This is just a "starting" point. It doesn't necessarily represent the best way to code something, but should contain
enough useful real-world example code to point you in the right direction. What it doesn't contain is very many
real-world business rules and error handling.

### Features
 - Person Search
    - search criteria
    - pagination
    - remembering the current criteria and page
 - Create/Edit Person
    - client-side validation rules
    - Save/Update of passwords securely
 - Authentication  (work in progress)


### ToDo
 - Security Roles & Permissions
 - Delete or Deactivate of a Person
 - Server-side Person validation rules
 - More example Unit Tests


# To use this project

1. Install Java JDK 1.7   (recommend C:\Java)
2. Install Gradle 2.2
 - set the GRADLE_HOME variable
 - add gradle home bin directory to your path
3. Install Git
 - add Git bin directory to your path
4. Install Node.js
5. Install a Java IDE like Intellij IDEA, Eclipse, NetBeans (recommend Intellij IDEA)

Checkout this project into some sort of projects/development folder (recommend C:\Projects\t1m)
 - can use IDE, or Git command line

5. Install SQL Server

* SQL Server Setup Tips
Before the application will work it's important that SQL Server works and you can get a JDBC connection working.
Depending on how and from where you have installed SQL Server you may need to check the following;
 * Windows Firewall allows inbound connections on 1433
 * Run "SQL Server Configuration Manager"
     - check that "SQL Server Network Configuration" > "Protocols for MSSQLSERVER" > "TCP/IP" is enabled
 * Right click on the Server instance node (topmost node) to bring up "Properties"
     - on the "Security" sub tab, check that "SQL Server and Windows Authentication mode" is enabled
 * Make sure you can connect with a JDBC client like DBVisualizer or Squirrel before attempting to fix problems with
   the code

6. Create a database for this application (see application.properties for database details)
 - The database tables required will be automatically created when the application server runs

7. Build the application with
 - IDE build menu options
 - Gradle "build"

Depending on your IDE you'll probably need to generate the querydsl source files with the "gradle build" command at least
once and then whenever your Domain/Entity classes change.

8. Run the main "Application" class
 - right-click on "nz.govt.doc.t1m.Application" - choose "Run Application.main()"

Assuming there are no build problems the server startup logging should get through to something like the following snippet.

```
2015-01-19 17:22:05.991  INFO 7452 --- [           main] n.g.d.t.c.MyWebMvcConfigurerAdapter      : Adding resource mapping: /resources/0.1.0/css/** -> classpath:/static/css/**
2015-01-19 17:22:05.996  INFO 7452 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/resources/0.1.0/css/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2015-01-19 17:22:05.996  INFO 7452 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/webjars/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2015-01-19 17:22:05.996  INFO 7452 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2015-01-19 17:22:06.029  INFO 7452 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**/favicon.ico] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2015-01-19 17:22:06.283  INFO 7452 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2015-01-19 17:22:06.328  INFO 7452 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 6080 (http)
2015-01-19 17:22:06.329  INFO 7452 --- [           main] nz.govt.doc.t1m.Application              : Started Application in 5.962 seconds (JVM running for 6.375)
```


9. Open the following link in your web browser

http://localhost:6080/


## Documentation Links

Various documentation links - sometimes more than one so to point you in the right direction of the most useful documentation.

AngularJS is the main MVC Web Framework, it uses JQuery underneath but you should try to ignore that almost always and
do things the "Angular" way.
* https://angularjs.org/
* https://code.angularjs.org/1.3.9/docs/guide
* https://code.angularjs.org/1.3.9/docs/api
* https://code.angularjs.org/1.3.9/docs/tutorial

Bootstrap is the CSS framework
* http://getbootstrap.com/

To Bootstrap easier to use with AngularJS a bunch of people wrote "Angular-UI" which joins Bootstrap and AngularJS together.
* http://angular-ui.github.io/
* http://angular-ui.github.io/bootstrap/

Spring Boot is a particular flavour of Spring which sits on top of the underlying Spring Framework
* http://spring.io/
* http://projects.spring.io/spring-boot/
* http://docs.spring.io/spring-boot/docs/1.2.1.RELEASE/reference/htmlsingle/
* http://docs.spring.io/spring-boot/docs/1.2.1.RELEASE/api/
* http://projects.spring.io/spring-framework/
* http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/
* http://docs.spring.io/spring/docs/current/javadoc-api/

Hibernate the object-relational-mapping framework. Load/save Java objects into a relational database. You should almost
always focus on the "JPA" way of doing things and avoid using the Hibernate classes directly.
* http://hibernate.org/orm/

QueryDSL is makes it a lot easier to write dynamic queries and is better than the equivalent JPA way of doing this. It's
worth the increase in complexity of adding another library on top of Hibernate.
* http://www.querydsl.com/
* http://www.querydsl.com/static/querydsl/3.6.0/reference/html/

