FROM openjdk:11-jdk-slim

#WORKDIR /app
#
#COPY . /app
## Add this line to update the permissions
#RUN chmod +x ./gradlew
#
#RUN ./gradlew build -x test
#
#ENTRYPOINT ["java", "-jar", "build/libs/Bipsync.jar"]
VOLUME /tmp

COPY build/libs/Bipsync-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]