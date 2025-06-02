FROM openjdk:17-jdk
ARG JAR_FILE=build/libs/*.jar
COPY SoundAlley-0.0.1-SNAPSHOT.jar app.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "app.jar"]