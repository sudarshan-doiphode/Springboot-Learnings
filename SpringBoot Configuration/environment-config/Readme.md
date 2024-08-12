# Using Environment Variables in Spring Boot Applications

---

## Introduction

Environment is another way to configure spring-boot properties, and it helps you to define properties dynamically for each environment. 
For example: For `Dev` env you can define different environment variable value, for `QA` you can define different variable value. 
Specifying values in environment variables can help you to securely add some properties.
This guide will explain how to use environment variables in a Spring Boot application, along with the advantages and disadvantages of this approach.

---

## How to Use Environment Variables in Spring Boot

1. **Setting Environment Variables**:
    - Environment variables can be set in different ways depending on your operating system or deployment environment. Here are some common methods:

    - **In Unix/Linux**:
      ```bash
      export SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/mydb
      ```

    - **In Windows**:
      ```cmd
      set SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/mydb
      ```

    - **In Docker**:
      You can pass environment variables to a Docker container using the `-e` flag:
      ```bash
      docker run -e SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/mydb myapp
      ```

2. **Accessing Environment Variables in Spring Boot**:

   Spring Boot automatically reads environment variables and makes them available in your application. Here’s how you can use them:

    - **Directly in `application.properties` or `application.yml`**:
      You can reference environment variables directly in your configuration files using `${}` syntax:
      ```properties
      spring.datasource.url=${SPRING_DATASOURCE_URL}
      ```

    - **Using `@Value` Annotation**:
      You can inject the value of an environment variable into a Spring component using the `@Value` annotation:
      ```java
      @Value("${SPRING_DATASOURCE_URL}")
      private String datasourceUrl;
      ```

    - **Using the `Environment` Object**:
      You can also access environment variables programmatically using Spring’s `Environment` object:
      ```java
      @Autowired
      private Environment env;
 
      public String getDatasourceUrl() {
          return env.getProperty("SPRING_DATASOURCE_URL");
      }
      ```

3. **Profiles and Environment Variables**:
    - You can also activate different profiles using environment variables:
      ```bash
      export SPRING_PROFILES_ACTIVE=prod
      ```

---

## Advantages of Using Environment Variables

1. **Separation of Configuration from Code**:
    - Environment variables allow you to keep configuration separate from your code, making your application more secure and easier to manage. Sensitive information like database credentials can be stored as environment variables instead of being hardcoded.

2. **Ease of Environment-Specific Configuration**:
    - Different environments (development, testing, production) can have different configurations, and environment variables make it easy to change these settings without modifying your codebase.

3. **Portability**:
    - Applications that rely on environment variables are more portable and can be easily deployed across different environments, such as local machines, CI/CD pipelines, or cloud platforms, without requiring changes to the code.

4. **Support for Cloud-Native Applications**:
    - Environment variables are widely used in cloud-native applications and containerized environments like Docker and Kubernetes, making them a standard way to configure applications in modern architectures.

---

## Disadvantages of Using Environment Variables

1. **Limited Scope and Visibility**:
    - Environment variables are typically limited in scope to the process that sets them. This can make them harder to manage, especially in complex systems where multiple processes need access to the same variables.

2. **Security Risks**:
    - While environment variables keep sensitive data out of your codebase, they can still be exposed if the environment is not properly secured. In some environments, environment variables can be inadvertently exposed through logs or process listings.

3. **Difficulty in Debugging**:
    - Debugging issues related to environment variables can be challenging, especially when variables are not set correctly or are missing. It may require deeper knowledge of the deployment environment.

4. **No Type Safety**:
    - Environment variables are essentially strings, which means there’s no type safety. You need to handle type conversion and validation in your code, which can introduce bugs if not done carefully.

---

## Conclusion

Using environment variables in Spring Boot applications provides a flexible and portable way to manage configuration properties, especially in cloud-native and containerized environments. However, it's essential to be aware of the potential disadvantages, such as security risks and difficulties in debugging. Proper management and security practices can help mitigate these risks and make the most of this powerful configuration tool.