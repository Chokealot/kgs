FROM --platform=linux/amd64 gradle:7.4.2-jdk17-alpine AS build
EXPOSE 8877
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootJar

FROM openjdk:17-jdk-alpine
EXPOSE 8877
RUN mkdir /jar
COPY --from=build /home/gradle/src/build/libs/*.jar /jar/app.jar
ENTRYPOINT ["java","-jar","/jar/app.jar"]