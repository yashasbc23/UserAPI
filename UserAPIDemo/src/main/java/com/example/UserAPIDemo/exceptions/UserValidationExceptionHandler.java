package com.example.UserAPIDemo.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * User validation exception to capture if any invalid entry is done by customer.
 */
@ControllerAdvice
public class UserValidationExceptionHandler extends ResponseEntityExceptionHandler {
    Logger log = LoggerFactory.getLogger(UserValidationExceptionHandler.class);
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        log.info("Validation error: One or more than one User information is not valid");
        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        UserApiError apiError =
                new UserApiError(HttpStatus.BAD_REQUEST,errors,ex.getLocalizedMessage());
        return handleExceptionInternal(
                ex, apiError, headers, apiError.getErrorCode(), request);
    }




}
