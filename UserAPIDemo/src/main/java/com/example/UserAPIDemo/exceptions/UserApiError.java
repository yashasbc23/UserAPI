package com.example.UserAPIDemo.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class UserApiError {

    private HttpStatus errorCode;
    private String errorDetail;
    private List<String> errorDesc;

    public UserApiError(HttpStatus status, List<String> errors, String message) {
        super();
        this.errorCode = status;
         this.errorDesc = errors;
        this.errorDetail = message;

    }

    public UserApiError(HttpStatus status, String error, String message) {
        super();
        this.errorCode = status;
        errorDesc = Arrays.asList(error);
        this.errorDetail = message;

    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(HttpStatus status) {
        this.errorCode = status;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String message) {
        this.errorDetail = message;
    }

    public List<String> getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(List<String> errors) {
        this.errorDesc = errors;
    }
}
