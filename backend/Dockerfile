FROM maven:3.9.9-eclipse-temurin-23-alpine AS build

WORKDIR /usr/src/app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM maven:3.9.9-eclipse-temurin-23-alpine AS production

WORKDIR /usr/src/app

COPY --from=build /usr/src/app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
