FROM openjdk:11
# EXPOSE 8088
# ADD target/springboot-oracle-docker.jar springboot-oracle-docker.jar
# ENTRYPOINT ["java","-jar","springboot-oracle-docker.jar"]



ADD target/springboot-oracle-docker.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]