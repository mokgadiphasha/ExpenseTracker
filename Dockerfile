FROM amazoncorretto:17-alpine

WORKDIR /app
COPY target/ExpenseTracker-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
