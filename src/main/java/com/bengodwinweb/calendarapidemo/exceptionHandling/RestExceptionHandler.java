package com.bengodwinweb.calendarapidemo.exceptionHandling;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

//    @Override
//    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        String error = "Malformed JSON request";
//        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
//    }

    private ResponseEntity<Object> buildResponseEntity (ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(EmailExistsException.class)
    protected ResponseEntity<Object> handleEmailExists(EmailExistsException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(UserDtoValidationException.class)
    protected ResponseEntity<Object> handleUserDtoValidation(UserDtoValidationException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());

        List<ApiSubError> subErrors = new ArrayList<>();
        for (FieldError fieldError : ex.getErrors().getFieldErrors()) {
            ApiSubError subError = new ApiSubError(fieldError.getField(), fieldError.getDefaultMessage());
            subErrors.add(subError);
        }
        apiError.setSubErrors(subErrors);

        return buildResponseEntity(apiError);
    }
}
