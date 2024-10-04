FROM maven:3.8.4-openjdk-17 as builder
WORKDIR ./app
COPY . /app/.
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/myjavaapp.jar /app/myjavaapp.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/myjavaapp.jar"]