# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-ea-1-jdk-oracle

# Set the working directory
WORKDIR /app

# Copy the project’s jar file to the container
COPY target/demo-0.0.1-SNAPSHOT.jar demo.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "demo.jar"]
