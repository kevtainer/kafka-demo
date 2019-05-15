FROM openjdk:11.0.1-jre

ADD maven/email-dispatch*.jar email-dispatch.jar

ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/urandom -jar /email-dispatch.jar

HEALTHCHECK --interval=15s --timeout=5s --start-period=300s \
  CMD curl -f http://localhost:8080/actuator/health || exit 1
