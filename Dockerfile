# Use an official Maven image with OpenJDK 17 as a base image
FROM maven:3.8.4-openjdk-17

# Set the working directory inside the container
WORKDIR /usr/src/app

# Copy the Maven project file
COPY pom.xml .

# Download and install dependencies
RUN mvn dependency:go-offline
do
# Copy the source code
COPY src ./src

# Build the application
RUN mvn package

# Expose the port that your application will run on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]
