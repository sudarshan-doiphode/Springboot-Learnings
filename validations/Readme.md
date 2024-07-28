## Spring Boot Starter Validations

### Overview

Validations in Spring Boot are essential for ensuring that the data entering your application meets predefined constraints. This helps maintain data integrity, enhance security, and improve the user experience. Spring Boot simplifies the implementation of validations with its powerful validation framework, allowing developers to enforce rules on data inputs using annotations and custom validators.

### Dependencies

Ensure you have the following dependencies in your `pom.xml` (for Maven) or `build.gradle` (for Gradle) file:

#### Maven

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

#### Gradle

```groovy
implementation 'org.springframework.boot:spring-boot-starter-validation'
```

### Basic Annotations

Spring Boot's validation framework leverages annotations from the Jakarta Bean Validation API. Below are some commonly used annotations:

1. **@NotNull**: Ensures that the annotated field is not null.
   ```java
   @NotNull(message = "Field cannot be null")
   private String field;
   ```

2. **@NotBlank**: Ensures that the annotated field is not null or empty after trimming whitespace.
   ```java
   @NotBlank(message = "Field cannot be blank")
   private String field;
   ```

3. **@Size**: Specifies the minimum and/or maximum length of a field.
   ```java
   @Size(min = 2, max = 30, message = "Field must be between 2 and 30 characters")
   private String field;
   ```

4. **@Min**: Specifies the minimum value for a numeric field.
   ```java
   @Min(value = 18, message = "Field must be at least 18")
   private Integer field;
   ```

5. **@Max**: Specifies the maximum value for a numeric field.
   ```java
   @Max(value = 100, message = "Field must be at most 100")
   private Integer field;
   ```

6. **@Email**: Ensures that the annotated field is a valid email address.
   ```java
   @Email(message = "Field should be a valid email")
   private String field;
   ```

### Custom Annotations

You can also create custom validation annotations. Here's how to create a custom password validator that enforces a password length of at least 9 characters, including letters, digits, and special characters `#@$`.

#### Custom Annotation

```java
package com.darsh.annotation;

import com.darsh.constraint.PasswordValidatorConstraint;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordValidatorConstraint.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidator {
    String message() default "Password must be at least 9 characters long, and include letters, digits, and special characters #@$";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
```

#### Constraint Validator

```java
package com.darsh.constraint;

import com.darsh.annotation.PasswordValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PasswordValidatorConstraint implements ConstraintValidator<PasswordValidator, String> {

    private static final Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[#@$]).{9,}$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && pattern.matcher(value).matches();
    }
}
```

### Example Model

Here’s an example of a model using various validation annotations:

```java
package com.darsh.model.request;

import com.darsh.annotation.PasswordValidator;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @NotBlank(message = "Name cannot be null")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String name;

    @NotBlank(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    @Min(value = 18, message = "Age should be at least 18")
    @Max(value = 100, message = "Age should be less than or equal to 100")
    private Integer age;

    @PasswordValidator
    private String password;
}
```

### Handling Validation Errors

You can handle validation errors globally using `@RestControllerAdvice`. Here’s an example:

```java
package com.darsh.controller.advice;

import com.darsh.model.response.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request,
            HttpServletRequest httpServletRequest) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        String requestPath = httpServletRequest.getRequestURI();

        ErrorResponse response = ErrorResponse.builder()
                .timestamp(LocalDate.now())
                .path(requestPath)
                .status(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .errors(errors)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}
```

### ErrorResponse Model

```java
package com.darsh.model.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.Map;

@Data
@Builder
public class ErrorResponse {
    private LocalDate timestamp;
    private String path;
    private HttpStatus status;
    private int statusCode;
    private Map<String, String> errors;
}
```

### Conclusion

This guide covers the basics of setting up and using validations in a Spring Boot application, including both built-in and custom validation annotations. By following these steps, you can ensure that the data entering your application is secure and adheres to your predefined constraints, thereby maintaining data integrity and enhancing the overall user experience.