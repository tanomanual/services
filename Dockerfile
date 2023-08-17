FROM openjdk:17
MAINTAINER root
RUN mkdir sistema
COPY target/*.jar sistema/service.jar
RUN mvn clean install -Dspring.profiles.active=prod
EXPOSE 9200
ENTRYPOINT ["java", "-jar", "/sistema/service.jar"]