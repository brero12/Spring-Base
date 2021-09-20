FROM openjdk:8-jdk-alpine

RUN apk add tzdata
ENV TZ=America/Bogota
VOLUME /tmp
COPY /target/infovisual-aurora-ms-gateway-0.1.jar .
EXPOSE 8443

CMD ["java","-jar","infovisual-aurora-ms-gateway-0.1.jar"]