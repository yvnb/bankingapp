# bankingapp
Design Considerations:

This Application is for creating Banking API for account transfer/deposit as well as display transactions for end users. 
This Application is based on Spring Boot with Swagger Open API 3.0 support and uses Inmemory HsqlDB for persistence with Spring Data JPA.
Users are authenticated using JWT token generated from user credentials and the Rest API's are secured with JWT token.

############ Sample User Data #########################
Please find the list of users created for testing the API's

admin/admin
user1/user1

Please find the Swagger UI for accessing the API's present in the system

https://bankingapi2785.herokuapp.com/swagger-ui/index.html

Spring Boot Actuator has support using micrometer which can be used for exporting the metrics to tools such as Prometheus (beyond this implementation) for observability and monitoring. 
Spring transaction Isolation Level is set to Read Committed which works with any of the standard RDMS like SQL, Oracle Server to prevent Lost Update Anamoly
Another way to stop multiple threads accessing the account balance is using optimistic locking with MVCC using @Version attribute which is beyond the current implementation
