FROM bellsoft/liberica-openjdk-alpine:21
WORKDIR /app
COPY ./Test.jar /app/test.jar
ENTRYPOINT ["java", "-jar", "test.jar"]