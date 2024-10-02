FROM openjdk:13-jdk-alpine
EXPOSE 9000
WORKDIR /opt/app
ARG JAR_FILE=target/MyCar/Application.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]