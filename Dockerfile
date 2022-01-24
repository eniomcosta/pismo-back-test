FROM openjdk:11
ADD target/pismo-back-test.jar pismo-back-test.jar
ENTRYPOINT ["java", "-jar","pismo-back-test.jar"]
EXPOSE 8080