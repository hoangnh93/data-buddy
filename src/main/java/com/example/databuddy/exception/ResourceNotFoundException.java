package com.example.databuddy.exception;

public class ResourceNotFoundException extends AppException{

    public ResourceNotFoundException(ErrorResponse error) {
        super(error);
    }
}
