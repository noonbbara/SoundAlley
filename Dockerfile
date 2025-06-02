FROM openjdk:17-jdk
ARG JAR_FILE=build/libs/SoundAlley-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "app.jar"]
