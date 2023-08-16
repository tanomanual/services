FROM openjdk:17
MAINTAINER root
RUN mkdir sistema
COPY target/*.jar sistema/service.jar
EXPOSE 9200
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "/sistema/service.jar"]