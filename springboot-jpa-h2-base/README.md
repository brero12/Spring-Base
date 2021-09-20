Base project with implementation of clean code recommendations.

It has:
* SOLID principles applied.
* Integration testing
* Swagger for service documentation
* Dockerfile for Docker imaging

Web Service is using - zuul gateway app- to change Path.

To download image:
docker pull brero12/springbootjpah2: v1

To execute:
docker run --rm -d -p 8080: 8080 --name springbooth2jpa brero12/springbootjpah2:v1

To verify documentation:
http://localhost:8080/swagger-ui.html

To try:

Original path:  http://ec2-18-223-117-185.us-east-2.compute.amazonaws.com:8081/swagger-ui.html
Behind zuul gateway: http://ec2-18-223-117-185.us-east-2.compute.amazonaws.com/ws/swagger-ui.html
