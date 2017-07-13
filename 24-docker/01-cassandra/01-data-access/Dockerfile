#  See the README for usage instructions

FROM java:8-jre

ENV JAR_FILE data-access-1.0-SNAPSHOT.jar

# Set the location of the jars
ENV JAR_HOME /usr/jars

# Copy your fat jar to the container
COPY target/$VERTICLE_FILE $JAR_HOME/

# Launch the jar
WORKDIR $JAR_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java $JAVA_OPTS -jar $JAR_FILE"]
