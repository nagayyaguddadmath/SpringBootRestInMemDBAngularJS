This is assignment given By UXP System to Nagayya Guddadamth

This application will be used for user management:
1) User REST services has been exposed with basic authontication (Services use spring Rest controller with JSON)
2) UI has been built using angularJS
3) Hibernate is used to for ORM of user entity
4) It uses in-Memory DB (hsqldb) for storing user detail like username, password and status.
5) Application has been developed using Spring Boot
6) Hibernate second level cache has been done using hazelcast cache
7) Password in database has been encoded using BASE64 encoding
8) Integration tests have been written using REST-ASSURED API's
9) Unit testing has been done using Mockito


To Run and Deploy locally using spring boot:
mvn spring-boot:run

After deployment, please open page using below URL
http://localhost:8080/uxpuser.html

servicecs are secured with basic authontication
Username is: admin
Password is: password

User credentials have been configured in java class SecurityConfiguration


TO Run all Test case and create war file, please run the below command
mvn clean install


Note: Please note that, you can add multiple users with same name using createUser Rest API (as we use userId as primary Key for USer). But find user will return the first user with given name.
This can be enhanced further at client side (by displaying all users created)

REST API can be accessed directly by below URL:
http://localhost:8080/user
