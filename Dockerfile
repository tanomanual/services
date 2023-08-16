FROM openjdk:17
MAINTAINER root
RUN mkdir sistema
ENV PROFILE=dev
COPY target/*.jar sistema/service.jar
EXPOSE 9200
ENTRYPOINT ["java", "-jar", "/sistema/service.jar", "--spring.profiles.active=${PROFILE}"]