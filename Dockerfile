FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/SistemaBotica-0.0.1.jar
COPY ${JAR_FILE} app-botica-backend.jar
ENTRYPOINT ["java","-jar","/app-botica-backend.jar"]