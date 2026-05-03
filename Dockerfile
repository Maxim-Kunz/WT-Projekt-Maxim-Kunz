#
# Build stage
#
FROM gradle:jdk25-jammy AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x test --no-daemon

#
# Package stage
#
FROM eclipse-temurin:25-jdk-jammy

COPY --from=build /home/gradle/src/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]