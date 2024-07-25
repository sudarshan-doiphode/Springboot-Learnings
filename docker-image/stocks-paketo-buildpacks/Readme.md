# Spring Boot Application with Paketo Buildpacks

This guide demonstrates how to build a Docker image for a Spring Boot application using Paketo Buildpacks with Maven or Gradle.

## Prerequisites

- **Docker**: Ensure Docker is installed on your system. You can download it from the [official Docker website](https://www.docker.com/products/docker-desktop).
- **Spring Boot Application**: Have a Spring Boot application ready.
- **Maven or Gradle**: Ensure your project uses either Maven or Gradle for building.

## Using Maven

### 1. Add the Spring Boot Maven Plugin

Add the Spring Boot Maven Plugin to your `pom.xml`:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <version>3.1.0</version> <!-- Use the version that matches your Spring Boot version -->
            <executions>
                <execution>
                    <goals>
                        <goal>build-image</goal>
                    </goals>
                </execution>
            </executions>
            <configuration>
                <image>
                    <name>yourdockerhubusername/my-spring-boot-app</name>
                </image>
            </configuration>
        </plugin>
    </plugins>
</build>
```

### 2. Build the Docker Image

Navigate to your project directory and run the following command to build the Docker image:

```bash
./mvnw spring-boot:build-image
```

This will use Paketo Buildpacks to build a Docker image for your Spring Boot application and tag it as `yourdockerhubusername/my-spring-boot-app`.

### 3. Run the Docker Container

Run the Docker container using the following command:

```bash
docker run -p 8080:8080 yourdockerhubusername/my-spring-boot-app
```

### 4. Access the Application

Open your web browser and navigate to `http://localhost:8080` to see your Spring Boot application running inside the Docker container.

## Using Gradle

### 1. Add the Spring Boot Gradle Plugin

Add the Spring Boot Gradle Plugin to your `build.gradle`:

```groovy
plugins {
    id 'org.springframework.boot' version '3.1.0' // Use the version that matches your Spring Boot version
    id 'io.spring.dependency-management' version '1.0.13.RELEASE'
}

springBoot {
    buildInfo()
    buildImage {
        imageName = 'yourdockerhubusername/my-spring-boot-app'
    }
}
```

### 2. Build the Docker Image

Navigate to your project directory and run the following command to build the Docker image:

```bash
./gradlew bootBuildImage
```

This will use Paketo Buildpacks to build a Docker image for your Spring Boot application and tag it as `yourdockerhubusername/my-spring-boot-app`.

### 3. Run the Docker Container

Run the Docker container using the following command:

```bash
docker run -p 8080:8080 yourdockerhubusername/my-spring-boot-app
```

### 4. Access the Application

Open your web browser and navigate to `http://localhost:8080` to see your Spring Boot application running inside the Docker container.

## Optional: Push to Docker Hub

If you want to share your Docker image, you can push it to Docker Hub.

### Tag the Image

Ensure the image is tagged correctly:

```bash
docker tag yourdockerhubusername/my-spring-boot-app yourdockerhubusername/my-spring-boot-app
```

### Push the Image

Push the image to Docker Hub:

```bash
docker push yourdockerhubusername/my-spring-boot-app
```

Replace `yourdockerhubusername` with your Docker Hub username.

## Conclusion

You've successfully created and run a Docker image for your Spring Boot application using Paketo Buildpacks with Maven or Gradle. This method leverages the integration of Buildpacks with your build tool to simplify the process. If you have any more questions or need further customization, feel free to ask!

---

This README provides a clear and concise guide for developers looking to use Paketo Buildpacks to containerize their Spring Boot applications using Maven or Gradle.