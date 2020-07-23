FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD /target/occo-products-0.0.1-SNAPSHOT.jar occo-products.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container", "-jar","occo-products.jar"]