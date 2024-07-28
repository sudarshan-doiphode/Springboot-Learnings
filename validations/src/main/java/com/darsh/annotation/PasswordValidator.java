package com.darsh.annotation;

import com.darsh.constraint.PasswordValidatorConstraint;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordValidatorConstraint.class)
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidator {
    String message() default "Password is Invalid, Length of password should be 9 characters 👎";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
