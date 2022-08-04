FROM openjdk:11
#ADD target/springboot-mongo-docker.jar app.jar
#ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8088
ADD /target/springboot-mongo-docker.jar springboot-mongo-docker.jar
ENTRYPOINT ["java","-jar","springboot-mongo-docker.jar"]