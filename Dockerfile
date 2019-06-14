FROM openjdk:8-jdk-alpine

COPY target/wallet.jar .

ENTRYPOINT ["java","-jar","wallet.jar"]
