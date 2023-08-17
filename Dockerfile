FROM openjdk:17
MAINTAINER root
RUN mkdir sistema
COPY target/*.jar sistema/service.jar
EXPOSE 9200
CMD ["-Dspring.profiles.active=dev"]
ENTRYPOINT ["java", "-jar", "/sistema/service.jar"]