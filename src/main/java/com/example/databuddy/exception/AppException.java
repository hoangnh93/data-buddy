package com.example.databuddy.exception;

public class AppException extends RuntimeException {
    private ErrorResponse error;

    public AppException(ErrorResponse error) {
        super();
        this.error = error;
    }

    public ErrorResponse getError() {
        return error;
    }

    public void setError(ErrorResponse error) {
        this.error = error;
    }
}
