FROM openjdk:8
EXPOSE 8088
ADD /target/springboot-oracle-docker.jar springboot-oracle-docker.jar
ENTRYPOINT ["java","-jar","springboot-oracle-docker.jar"]