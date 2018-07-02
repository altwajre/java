#  See the README for usage instructions

FROM java:8-jre

ENV SPRING_FILE swagger-1.0-SNAPSHOT.jar

# Set the location of the SPRINGs
ENV SPRING_HOME /usr/SPRINGs

EXPOSE 8080

# Copy your fat jar to the container
COPY target/$SPRING_FILE $SPRING_HOME/

# Launch the SPRING
WORKDIR $SPRING_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java $JAVA_OPTS -jar $SPRING_FILE"]
