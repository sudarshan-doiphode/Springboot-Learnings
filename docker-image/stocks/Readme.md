# Stocks Application Docker Setup

## Introduction

This README provides detailed instructions on how to create a Dockerfile, 
build a Docker image for your Spring Boot application, and run the image in a Docker container. 
It also covers how to start and stop the container.

## What is a Dockerfile?

A Dockerfile is a script containing a series of instructions on how to build a Docker image. 
It defines the base image to use, application dependencies, environment configurations, and the command to run the application. Docker uses these instructions to create a reproducible and portable image.

## Creating a Dockerfile

### Dockerfile Example

Here is a sample Dockerfile for your Spring Boot application:

```Dockerfile
# Use an official Eclipse Temurin runtime as a parent image
FROM eclipse-temurin:17-jre

# Extra info metadata like who is the author
LABEL maintainer="sudarshandoiphode"

# Copy the jar file into the docker work directory which is root in our case
COPY target/stocks-0.0.1-SNAPSHOT.jar stocks-0.0.1-SNAPSHOT.jar

# Command to run the application in the container
CMD ["java", "-jar", "stocks-0.0.1-SNAPSHOT.jar"]
```

### Steps to Create a Dockerfile

1. **Open your project directory**: Navigate to the root of your project directory where your Spring Boot application is located.
2. **Create a file named `Dockerfile`**: Ensure the file has no extension.
3. **Add the Dockerfile content**: Copy and paste the above Dockerfile content into your `Dockerfile`.

## Building the Docker Image

To build the Docker image, run the following command in your terminal:

```sh
docker build -t stocks-app .
```

### Explanation of the Build Command

1. `docker build`: This command is used to build an image from a Dockerfile.
2. `-t stocks-app`: This option tags the image with the name `stocks-app`. You can replace `stocks-app` with any name you prefer.
3. `.`: The dot at the end of the command specifies the build context, which is the current directory (where the Dockerfile is located).

## Running the Docker Image in a Container

To run the Docker image in a container, use the following command:

```sh
docker run -d -p 8080:8080 --name my-stocks-app stocks-app
```

### Explanation of the Run Command

1. `docker run`: This command is used to run a container from a Docker image.
2. `-d`: Runs the container in detached mode (in the background).
3. `-p 8080:8080`: Maps port 8080 on the host to port 8080 on the container.
4. `--name my-stocks-app`: Assigns a name to the container for easier management.
5. `stocks-app`: The name of the Docker image to run.

### Verify the Application is Running

You can verify that your application is running by accessing `http://localhost:8080` in your web browser or by using a tool like `curl`:

```sh
curl http://localhost:8080/stocks
```

## Managing the Docker Container

### Viewing Running Containers

To check if your container is running, use:

```sh
docker ps
```

### Viewing Container Logs

To view the logs of your running container:

```sh
docker logs my-stocks-app
```

### Stopping the Container

To stop the container, use:

```sh
docker stop my-stocks-app
```

### Starting the Container

To start the container again after stopping it, use:

```sh
docker start my-stocks-app
```

### Removing the Container

To remove the container when you no longer need it, use:

```sh
docker rm my-stocks-app
```

---

By following these steps, you should be able to create a Dockerfile, build a Docker image, and manage the lifecycle of your Docker container effectively. If you encounter any issues or need further assistance, feel free to ask!