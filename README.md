# DatabaseService

## General info

This project is an admin panel for secure interaction with the databases of car dealerships.

## Used technologies

The project is written using JDK 21 and the Spring framework (Spring Boot, Spring Data, Spring MVC). It is a backend application that include server-side component, which work with PostgreSQL database server and MongoDB database server. The server provides a RESTful API built according to the MVC model. For database operations, the server uses Spring Data JPA, Hibernate, and JDBC. The backend includes Data Transfer Objects (DTOs) and mappers based on MapStruct. The server features data validation, defined exceptions, and their handler.

## Application structure

Application consists of next parts:

- backend: backend server application that provides RESTfull API for CRUD-operation with databases
- PostgreSQL server: hosts SQL database
- MongoDB server: hosts noSQL database

## Launching and usage

1. Clone this repository:

`git clone https://github.com/voewoda88/DatabaseService`

2. Change current directory to DatabaseService:

`cd DatabaseService`

3. Launch maven:
   
`mvn spring-boot:run`

4. Open any suitable browser. Next you can go to the next URL:
   - http://localhost:8080/swagger-ui/index.html - to see documentation and test the server API
  
   Note: defaults ports are:

   - port 8080 - Backend application
   - port 5432 - PostgreSQL server
   - port 27017 - MongoDB server

   But you can change them in application.properties

5. All done!
   
