FROM openjdk:11
EXPOSE 8088
ADD target/springboot-mongo-docker.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]

