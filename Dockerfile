FROM openjdk:17
ENV PORT 9090
COPY target/UploadFileToS3*.jar /opt/UploadFileToS3.jar
WORKDIR /opt
ENTRYPOINT exec java $JAVA_OPTS -jar UploadFileToS3.jar