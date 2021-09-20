# run mvn project
#RUN mvn clean package

# create image
FROM openjdk:8-jdk-alpine
MAINTAINER https://github.com/brero12/
RUN apk add tzdata
ENV TZ=America/Bogota
VOLUME /tmp
COPY /target/springboot-jpa-h2-base-0.0.1-SNAPSHOT.jar .
EXPOSE 8080

CMD ["java","-jar","springboot-jpa-h2-base-0.0.1-SNAPSHOT.jar"]
