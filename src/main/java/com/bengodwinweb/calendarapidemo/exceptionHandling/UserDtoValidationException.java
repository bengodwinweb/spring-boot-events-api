package com.bengodwinweb.calendarapidemo.exceptionHandling;

import org.springframework.validation.Errors;

public class UserDtoValidationException extends Exception {
    private Errors errors;

    public UserDtoValidationException(String message, Errors errors) {
        super(message);
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }
}
