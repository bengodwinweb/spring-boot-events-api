package com.bengodwinweb.calendarapidemo.exceptionHandling;

public class ApiSubError {
    private String field;
    private String message;

    ApiSubError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    ApiSubError(String message) {
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
