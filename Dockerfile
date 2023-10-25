FROM openjdk:17
WORKDIR /app
ADD /target/platform_mvp-0.0.1-SNAPSHOT.jar backend.jar
EXPOSE 8181
ENTRYPOINT ["java", "-jar", "backend.jar"]



