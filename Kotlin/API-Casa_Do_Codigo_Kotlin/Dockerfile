FROM adoptopenjdk/openjdk11
EXPOSE 8080
ADD build/libs/*.jar cdc.jar
ENTRYPOINT ["java", "-jar", "cdc.jar"]