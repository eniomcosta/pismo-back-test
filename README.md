[![Build Status](https://app.travis-ci.com/eniomcosta/pismo-back-test.svg?branch=main)](https://app.travis-ci.com/eniomcosta/pismo-back-test)
[![codecov](https://codecov.io/gh/eniomcosta/pismo-back-test/branch/main/graph/badge.svg?token=CONN3YL3ZN)](https://codecov.io/gh/eniomcosta/pismo-back-test)
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
- Data builders for cleaner object instantiations

## Running the application

### Starting application
- Execute Docker command: `docker-compose up -d --build`

### Stoping application
- Execute Docker command: `docker-compose down -v`

### Running tests [^2]
- Execute maven command: `mvn test` 

## Tools URLs [^1]
### Swagger UI
- http://localhost:8080/swagger-ui.html

### H2 Database UI [^3]
- http://localhost:8080/h2-console


[^1]: With application running locally
[^2]: The `mvn install` command already execute the tests
[^3]: Use `jdbc:h2:mem:testdb` as JDBC URL

