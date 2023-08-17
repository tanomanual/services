FROM openjdk:17
MAINTAINER root
RUN mkdir sistema
ENV SPRING_PROFILES_ACTIVE=prod
COPY target/*.jar sistema/service.jar
EXPOSE 9200
ENTRYPOINT ["java", "-jar", "/sistema/service.jar"]