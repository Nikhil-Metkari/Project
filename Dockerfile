FROM openjdk:11
EXPOSE 8088
ADD target/docker-oracle-docker.jar docker-oracle-docker.jar
ENTRYPOINT ["java","-jar","/docker-oracle-docker.jar"]