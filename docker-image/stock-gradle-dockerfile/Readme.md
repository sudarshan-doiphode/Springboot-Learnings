# Getting Started Guide for Creating a Docker Image of a Spring Boot Gradle Application

This guide will walk you through the steps needed to create a Docker image for your Spring Boot Gradle application using the provided Dockerfile. Docker allows you to package your application and its dependencies in a virtual container that can run on any platform that supports Docker.

## Prerequisites

- Basic knowledge of Spring Boot and Gradle.
- Docker installed on your machine. You can download Docker from [here](https://www.docker.com/products/docker-desktop).

## Step 1: Create a Spring Boot Application

If you don't already have a Spring Boot application, you can create one using Spring Initializr. Here's a basic example:

1. Go to [Spring Initializr](https://start.spring.io/).
2. Select "Gradle Project" and "Java".
3. Add the necessary dependencies for your project (e.g., Spring Web).
4. Click "Generate" to download the project.
5. Extract the downloaded zip file and open it in your favorite IDE.

Alternatively, you can create a basic Spring Boot application using the following directory structure:

```
springboot-docker/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── darsh/
│   │   │           └── StockGradleDockerfileApplication.java
│   │   └── resources/
│   │       └── application.properties
├── build.gradle
└── settings.gradle
```

### Example `DemoApplication.java`

```java
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class StockGradleDockerfileApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

@RestController
class HelloWorldController {
    @GetMapping("/")
    public String hello() {
        return "Hello, World!";
    }
}
```

### Example `build.gradle`

```groovy
plugins {
    id 'org.springframework.boot' version '3.1.2'
    id 'io.spring.dependency-management' version '1.1.0'
    id 'java'
}

group = 'com.example'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '17'

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

test {
    useJUnitPlatform()
}
```

## Step 2: Build the Spring Boot Application

Before you create the Docker image, you need to build the Spring Boot application. Open a terminal, navigate to your project directory, and run the following command:

```sh
./gradlew build
```

This command will compile your code and package it into a JAR file located in the `build/libs` directory.

## Step 3: Create a Dockerfile

In the root of your Spring Boot project, create a file named `Dockerfile` with the following content:

```dockerfile
# Use an official Eclipse Temurin runtime as a parent image
FROM eclipse-temurin:17-jdk

# Extra info metadata like who is the author
LABEL maintainer="sudarshandoiphode"

# Copy the jar file into the docker work directory which is root in our case
COPY build/libs/stock-gradle-dockerfile-0.0.1-SNAPSHOT.jar stock-gradle-dockerfile-0.0.1-SNAPSHOT.jar

# Command to run the application in the container
CMD ["java", "-jar", "stock-gradle-dockerfile-0.0.1-SNAPSHOT.jar"]
```

## Step 4: Build the Docker Image

Open a terminal, navigate to your project directory, and run the following command to build the Docker image:

```sh
docker build -t springboot-gradle-docker .
```

This command tells Docker to build an image using the Dockerfile in the current directory (`.`) and tag it as `springboot-gradle-docker`.

## Step 5: Run the Docker Container

Once the Docker image is built, you can run it using the following command:

```sh
docker run -p 8080:8080 springboot-gradle-docker
```

This command tells Docker to run the container and map port 8080 of the container to port 8080 on your local machine.

## Step 6: Verify the Application

Open a web browser and navigate to `http://localhost:8080`. You should see the message `Hello, World!` from your Spring Boot application.

## Conclusion

You've successfully created a Docker image for your Spring Boot Gradle application and ran it in a Docker container. This setup allows you to easily distribute and deploy your application in a consistent environment.

You can further customize your Dockerfile and Gradle build script as needed for your specific application requirements.