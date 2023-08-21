FROM tianyifu/my_base_image:latest

COPY python /python

COPY target/Talkative-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]



