package com.example.UserAPIDemo.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Exception class to capture if user is not present in our system.
 */
public class UserNotFoundException extends  ResponseEntityExceptionHandler {

    private HttpStatus errorCode;
    private String errorDetail;
    private List<String> errorDesc;
    public UserNotFoundException(HttpStatus status, List<String> errors, String message){
        this.errorCode = status;
        this.errorDesc = errors;
        this.errorDetail = message;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

       /* UserNotFoundException apiError =
                new UserNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR,errors,ex.getLocalizedMessage());
        return handleExceptionInternal(
                ex, apiError, headers, apiError.getErrorCode(), request);*/
        return null;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(HttpStatus errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }

    public List<String> getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(List<String> errorDesc) {
        this.errorDesc = errorDesc;
    }
}
