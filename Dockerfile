FROM my_base_image


COPY python /python

COPY target/Talkative-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]



