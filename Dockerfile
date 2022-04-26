FROM --platform=linux/amd64 gradle:7.3-jdk17-alpine AS build
EXPOSE 9988
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootJar

FROM openjdk:17-jdk-alpine
EXPOSE 9988
RUN mkdir /jar
COPY --from=build /home/gradle/src/build/libs/*.jar /jar/app.jar
ENTRYPOINT ["java","-jar","/jar/app.jar"]