#### Read [README.md](/README.md) for Minimum Requirements.

#### Import as maven project to your IDE

#### Set JAVA version in [pom.xml](pom.xml) and clean build with maven
	<java.version>1.8</java.version>

#### Refresh Project and Run "fun.zepo.auth.WebApplication" as __Java Application__

#### __Embedded__ Tomcat is being used for this application


#### Default Properties [ Change/Modify Properties File](/src/main/resources/application.properties)
*	PORT
		server.port=7070

*	HIBERNATE DDL-AUTO
		spring.jpa.hibernate.ddl-auto=update

*	MySQL 
		spring.datasource.url=jdbc:mysql://localhost:3306/zepo
		spring.datasource.username=zepo
		spring.datasource.password=zepo


## [UI Screenshots](SCREENSHOTS)


## To Run API Tests on postman open html file at:


- [zepo-login/SCREENSHOTS/zepo api postman runner.html](/SCREENSHOTS/zepo api postman runner.html)




