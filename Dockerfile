FROM openjdk:21
ARG JAR_FILE=build/libs/*.jar
COPY ./build/libs/chess-0.0.1.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]