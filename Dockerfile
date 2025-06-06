FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# Defina argumentos para o token e username
ARG GITHUB_USERNAME
ARG GITHUB_TOKEN

# Crie o settings.xml dinamicamente com as credenciais
RUN mkdir -p /root/.m2

COPY . /app/

RUN echo "<settings><servers><server><id>github</id><username>${GITHUB_USERNAME}</username><password>${GITHUB_TOKEN}</password></server></servers></settings>" > /root/.m2/settings.xml

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

ENV JAVA_OPTS="-Xms256m -Xmx512m"
EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
