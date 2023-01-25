FROM maven:3.8.6-jdk-11 as builder

COPY ./ /app
RUN cd /app && mvn package

FROM openjdk:11-jre-slim

ARG JAR_FILE=/app/api-builder-app/target/*.jar
COPY --from=builder ${JAR_FILE} /api-builder/app.jar

#Launch app
ENTRYPOINT ["java","-jar","/api-builder/app.jar"]