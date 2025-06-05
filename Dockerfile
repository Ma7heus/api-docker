FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

COPY . /app/
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

ENV JAVA_OPTS="-Xms256m -Xmx512m"
EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
