FROM openjdk:17-alpine

COPY target/CreditZaimTest-*-SNAPSHOT.jar /app/app.jar

CMD ["java", "-jar", "/app/app.jar"]