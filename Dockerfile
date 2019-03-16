FROM maven:3.6.0-jdk-8-alpine as builder
COPY pom.xml    pom.xml
RUN mvn dependency:resolve
RUN mvn dependency:resolve-plugins

COPY src    src

RUN mvn clean package


# Start with a base image containing Java runtime
FROM openjdk:8-jre-alpine

# Add Maintainer Info
LABEL maintainer="vinicius_ufop@yahoo.com.br"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 9001 available to the world outside this container
EXPOSE 9001

COPY --from=builder target/provider-mock-0.0.1-SNAPSHOT.jar  provider-mock.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/provider-mock.jar"]