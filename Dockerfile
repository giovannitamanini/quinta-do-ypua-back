FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder

WORKDIR /app

COPY . .

RUN mvn package

FROM eclipse-temurin:17-jdk-alpine

COPY --from=builder /app/target/pousada-0.0.1-SNAPSHOT.jar /pousada-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/pousada-0.0.1-SNAPSHOT.jar"]
