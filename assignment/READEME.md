This is assignment given By UXP System to Nagayya Guddadamth

This application will be used for user management:
1) It has exposed secured rest services (using Rest controller)
2) UI has been built using angularJS
3) Hibernate is used to for ORM of user entity
4) it uses in-Memory DB (hsqldb) for storing user detail like username, password and status.
5) Rest services are secured with Basic authontication
6) Application has been developed using Spring Boot

To Run and Deploy locally using spring boot:
mvn spring-boot:run

After deployment, please open page using below URL
http://localhost:8080/index.html

servicecs are secured with basic authontication
Username is: admin
Password is: password

USer credentials have been configured in java class SecurityConfiguration

TODO Tasks:
1) JUNIT - need to be written
2) Introduce 2nd level cache in hibernate