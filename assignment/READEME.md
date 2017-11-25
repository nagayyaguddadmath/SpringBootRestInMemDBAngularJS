This is assignment given By UXP System to Nagayya Guddadamth

This application will be used for user management:
1) It has exposed secured rest services (using spring Rest controller with JSON)
2) UI has been built using angularJS
3) Hibernate is used to for ORM of user entity
4) it uses in-Memory DB (hsqldb) for storing user detail like username, password and status.
5) Rest services are secured with Basic authentication
6) Application has been developed using Spring Boot
7) Integration tests have been written using REST-ASSURED API's

To Run and Deploy locally using spring boot:
mvn spring-boot:run

After deployment, please open page using below URL
http://localhost:8080/index.html

servicecs are secured with basic authontication
Username is: admin
Password is: password

USer credentials have been configured in java class SecurityConfiguration

TO Run Test case and create war file please run the below command
mvn clean install

TODO Tasks:
1) Introduce 2nd level cache in hibernate
2) Password encoding in databse