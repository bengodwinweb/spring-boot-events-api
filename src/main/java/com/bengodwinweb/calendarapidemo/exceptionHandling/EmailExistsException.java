package com.bengodwinweb.calendarapidemo.exceptionHandling;

public class EmailExistsException extends Exception {
    public EmailExistsException() {
        super("Email already exists");
    }
}
