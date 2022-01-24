# Pismo Back Test 3.0
Pismo's backend test challenge

## Description
This application is part of Pismo's backend challenge test. 

## Technologies
- [Spring Boot - 2.6.3](https://spring.io/projects/spring-boot) - Creates stand-alone Spring application
- [Java 11](https://www.oracle.com/br/java/) - Software development language
- [JUnit 5](https://junit.org/junit5/docs/current/user-guide/) - Unit tests library
- [Mockito](https://site.mockito.org/) - Unit test mock behavior
- [Maven](https://maven.apache.org/) - Dependency manager
- [Swagger 2](https://swagger.io/docs/) - Endpoints documentation and UI
- [H2 Database](https://www.h2database.com/html/quickstart.html) - In-memory database
- [Docker](https://www.docker.com/get-started) - Container virtualization
- [Docker Compose](https://docs.docker.com/compose/install/) - Tool for run Docker applications

## Project architecture
- 3-Tier (presentation, business, data)
- Data Transfer Object as representative modeling for the external world
- Centralized REST exceptions

## Running the application

### Creating JAR file
- Execute maven command: `mvn clean package `
- It will create a JAR file inside `/target` called `pismo-back-test.jar`

### Starting the application
- Execute Docker command: `docker-compose up -d --build`

### Stoping the application
- Execute Docker command: `docker-compose down`

## Useful links
###Swagger UI
- http://localhost:8080/swagger-ui.html

### H2 Database UI
- http://localhost:8080/h2-database