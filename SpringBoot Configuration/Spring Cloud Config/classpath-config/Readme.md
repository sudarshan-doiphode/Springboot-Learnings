# Getting Started with Spring Cloud Config Classpath Demo

To get started with Spring Cloud Config, 
you'll need to set up both a Config Server and a Config Client. 
This guide will walk you through the process step by step.

### **1. Setting Up the Config Server**

The Config Server is responsible for serving configuration properties to client applications. 
It can source configuration from various places like Git, local file systems, or even classpath resources.

#### **Step 1.1: Create a Spring Boot Application for Config Server**

1. **Create a new Spring Boot project** using Spring Initializr (https://start.spring.io/):
    - **Project:** Maven Project
    - **Language:** Java
    - **Spring Boot:** Choose the latest stable version.
    - **Dependencies:** Select "Config Server"

2. **Configure the `pom.xml` or `build.gradle`**:
   If you're using Maven, ensure your `pom.xml` includes the Spring Cloud dependencies:
   ```xml
   <dependencies>
       <dependency>
           <groupId>org.springframework.cloud</groupId>
           <artifactId>spring-cloud-config-server</artifactId>
       </dependency>
   </dependencies>

   <dependencyManagement>
       <dependencies>
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-dependencies</artifactId>
               <version>${spring-cloud.version}</version>
               <type>pom</type>
               <scope>import</scope>
           </dependency>
       </dependencies>
   </dependencyManagement>
   ```

3. **Enable the Config Server** in your main application class:
   ```java
   import org.springframework.boot.SpringApplication;
   import org.springframework.boot.autoconfigure.SpringBootApplication;
   import org.springframework.cloud.config.server.EnableConfigServer;

   @SpringBootApplication
   @EnableConfigServer
   public class ConfigServerApplication {
       public static void main(String[] args) {
           SpringApplication.run(ConfigServerApplication.class, args);
       }
   }
   ```

4. **Configure the Config Server** in `application.yml`:
   For this example, we'll use configuration files stored in the classpath:
   ```yaml
   server:
     port: 8888

   spring:
     profiles:
       active: native
     cloud:
       config:
         server:
           native:
             search-locations: classpath:/config  # Over here classpath refers to resources folder in your project
   ```

    - The `native` profile tells Spring Cloud Config to look for configuration files in the specified `search-locations`.
    - `classpath:/config` points to a `config` directory inside the classpath where your configuration files will be stored.

5. **Create the Configuration Files**:
   In the `src/main/resources` directory, create a `config` directory. Inside this directory, create files like `application.yml`, `application-dev.yml`, `application-prod.yml`, etc.
    - **Example: `application.yml`**:
      ```yaml
      greeting:
        message: "Hello from Config Server"
      ```
    - **Example: `application-dev.yml`**:
      ```yaml
      greeting:
        message: "Hello from Config Server - Dev Profile"
      ```

6. **Run the Config Server**:
    - Build and run your application.
    - The Config Server will now serve configuration properties from the classpath.

### **2. Setting Up the Config Client**

The Config Client fetches configuration properties from the Config Server.

#### **Step 2.1: Create a Spring Boot Application for Config Client**

1. **Create a new Spring Boot project** using Spring Initializr:
    - **Project:** Maven Project
    - **Language:** Java
    - **Spring Boot:** Choose the latest stable version.
    - **Dependencies:** Select "Config Client" and "Spring Web."

2. **Configure the `pom.xml` or `build.gradle`**:
   If you're using Maven, ensure your `pom.xml` includes the Spring Cloud dependencies:
   ```xml
   <dependencies>
       <dependency>
           <groupId>org.springframework.cloud</groupId>
           <artifactId>spring-cloud-starter-config</artifactId>
       </dependency>
   </dependencies>

   <dependencyManagement>
       <dependencies>
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-dependencies</artifactId>
               <version>${spring-cloud.version}</version>
               <type>pom</type>
               <scope>import</scope>
           </dependency>
       </dependencies>
   </dependencyManagement>
   ```

3. **Configure the Config Client** in `application.yml`:
   ```yaml
   spring:
     application:
       name: config-client
     profiles:
       active: prod
     config:
       import: configserver:http://localhost:8888
     
   server:
     port: 8080
   ```

    - `spring.application.name` is used by the Config Server to locate the correct configuration file.
    - `spring.cloud.config.uri` points to the Config Server.

4. **Create a Controller to Use the Config Properties**:
   In your main application, create a controller to demonstrate how to use the externalized properties:
   ```java
   import org.springframework.beans.factory.annotation.Value;
   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.RestController;

   @RestController
   public class GreetingController {

       @Value("${greeting.message}")
       private String greetingMessage;

       @GetMapping("/greet")
       public String greet() {
           return greetingMessage;
       }
   }
   ```

5. **Run the Config Client**:
    - Start the Config Client application.
    - The client will fetch its configuration from the Config Server.
    - Visit `http://localhost:8080/greet` (assuming the client runs on port 8080) to see the greeting message.

### **3. Testing the Setup**

#### **Step 3.1: Access Configuration Properties**

1. Run the Config Server first.
2. Run the Config Client.
3. Visit `http://localhost:8080/greet` to see the greeting message. This message is fetched from the Config Server.

#### **Step 3.2: Switch Profiles**

To test different profiles, modify the `spring.profiles.active` property on the client side:
- **Example**: Set the profile to `dev` by adding this in the `application.yml` of the Config Client:
  ```yaml
  spring:
    profiles:
      active: dev
  ```
- Restart the Config Client and visit `http://localhost:8080/greet` to see the profile-specific message.

### **Summary**

- **Config Server**: Centralizes the management of configuration files and serves them to Config Clients.
- **Config Client**: Fetches configuration from the Config Server, allowing dynamic and centralized configuration management.
- **Profiles and Externalized Configuration**: Simplify managing multiple environments, reducing the need to manually change configuration files in the codebase.