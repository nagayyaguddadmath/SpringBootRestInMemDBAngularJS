This is assignment completed by Nagayya Guddadamth

This application will be used for user management:
1) User REST services has been exposed with basic authentication (Services use spring Rest controller with JSON)
2) UI has been built using angularJS+HTML
3) Hibernate is used to for ORM of user entity
4) It uses in-Memory DB (hsqldb) for storing user entity details like username, password and status.
5) Application has been developed using Spring Boot
6) Hibernate second level cache has been done using hazelcast cache
7) Password in database has been encoded using BASE64 encoding
8) Integration tests have been written using REST-ASSURED API's (class name: UserControllerIntegrationTest)
9) Unit testing has been done using Mockito (class name: UserServiceUnitTest)


To Run and Deploy locally using spring boot:
mvn spring-boot:run

After deployment, please open page using below URL
http://localhost:8080/uxpuser.html

services are secured with basic authentication
Username is: admin
Password is: password

User credentials have been configured in java class SecurityConfiguration


TO Run all Test case and create war file, please run the below command
mvn clean install


Note: Please note that, you can add multiple users with same name using createUser Rest API (as we use userId as primary Key for User). But find user API will return the first user with given name.
This can be enhanced further at client side (by displaying all users created)

After deployment, REST API can be accessed directly by below URL:
http://localhost:8080/user

This code is not production ready because of below reasons:
1) Junit coverage has to be increased
2) Logging is not yet implemented
3) Eventhough server side allows users with same name, client side needs to be enhanced to handle this
4) hibernate 2nd level cache should to be checked for the optimum performance
5) 
