package com.bengodwinweb.calendarapidemo.Model;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface ValidPassword {
    String message() default "Password must be at least 6 characters long and contain an uppercase letter, lowercase letter, and number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
