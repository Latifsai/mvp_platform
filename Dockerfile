FROM openjdk:17
EXPOSE 8181
ADD /target/platform_mvp-0.0.1-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]
