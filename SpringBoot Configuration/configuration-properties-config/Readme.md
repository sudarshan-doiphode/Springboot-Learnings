# Getting Started with `@ConfigurationProperties` in Spring Boot

## Introduction

In Spring Boot, configuration properties allow you to externalize your configuration so that you can manage it outside of your codebase. The `@ConfigurationProperties` annotation is a powerful feature that lets you bind a group of related properties into a Java object, making it easier to manage and use them throughout your application.

## What is `@ConfigurationProperties`?

`@ConfigurationProperties` is an annotation in Spring that allows you to map a group of properties from an external configuration file (like `application.yml` or `application.properties`) into a Java class. This annotation helps in organizing configuration properties and makes your code more maintainable and less error-prone.

### Basic Example

Suppose you have the following configuration in your `application.yml`:

```yaml
user:
  email: darsh@example.com
  address:
    city: Pune
    state: Maharashtra
    country: India
  mobile:
    - 9090909090
    - 8080808080
```

You can map these properties into a Java class using `@ConfigurationProperties`:

```java
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

@Component
@ConfigurationProperties(prefix = "user")
@Data
public class ApplicationConfig {
    private String email;
    private Map<String, String> address;
    private List<Long> mobile;
}
```

### Why Are Setters and Getters Required?

When you use `@ConfigurationProperties`, Spring needs to set the values of the properties in your class. For this to happen, Spring uses the setter methods. If your class only has fields and no setters, Spring won't be able to inject the values from your configuration file.

Similarly, getters are required if you want to access these values later in your code. The `@Data` annotation from Lombok automatically generates getters and setters for you, which is why it's commonly used with `@ConfigurationProperties`.

### Advantages of `@ConfigurationProperties` Over `@Value`

1. **Grouping of Properties**: `@ConfigurationProperties` allows you to group related properties into a single class. This makes your configuration more organized and manageable, especially when dealing with a large number of properties. On the other hand, `@Value` is typically used to inject individual properties directly into fields, which can become cumbersome if you have many properties.

2. **Type Safety**: With `@ConfigurationProperties`, Spring automatically converts property values to the appropriate data type. For example, if your property is a number, it will be converted to `Integer` or `Long`. This type safety reduces the chances of errors in your application.

3. **Default Values**: You can define default values in your class for properties that might not be set in the configuration file. This is easier with `@ConfigurationProperties` as you have full control over the class. With `@Value`, you would need to specify default values in the annotation itself.

4. **Nested Properties**: `@ConfigurationProperties` supports nested properties out of the box. This means you can map complex structures (like lists and maps) directly into your Java objects. While this is possible with `@Value`, it's much more cumbersome and not as clean.

### Example of Using `@ConfigurationProperties` in a Controller

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final ApplicationConfig config;

    @GetMapping("/user")
    public ResponseEntity<ApplicationConfig> getUserConfig() {
        return ResponseEntity.ok(config);
    }
}
```

In this example, `ApplicationConfig` is injected into the controller, and you can access the user properties directly.

## Conclusion

`@ConfigurationProperties` is a powerful tool in Spring Boot that helps you manage your configuration in an organized, type-safe, and maintainable way. It is especially useful when dealing with complex configurations and a large number of properties. While `@Value` is handy for simple cases, `@ConfigurationProperties` offers a more structured and scalable approach for managing application configuration.

By using `@ConfigurationProperties`, you make your application more flexible, maintainable, and less prone to errors, which are key benefits for any Spring Boot developer.