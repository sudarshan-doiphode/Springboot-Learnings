# Use an official Eclipse Temurin runtime as a parent image
FROM eclipse-temurin:17-jdk

# Extra info metadata like who is the author
LABEL maintainer="sudarshandoiphode"

# Copy the jar file into the docker work directory which is root in our case
COPY build/libs/stock-gradle-dockerfile-0.0.1-SNAPSHOT.jar stock-gradle-dockerfile-0.0.1-SNAPSHOT.jar

# Command to run the application in the container
CMD ["java", "-jar", "stock-gradle-dockerfile-0.0.1-SNAPSHOT.jar"]
