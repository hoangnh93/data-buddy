package com.example.databuddy.exception;

import com.example.databuddy.config.MessageProperties;
import com.example.databuddy.util.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageProperties messageProperties;

    public AppExceptionHandler(MessageProperties messageProperties) {
        this.messageProperties = messageProperties;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ResponseEntity<?> handlerAppException(AppException e) {
        return getExceptionResponse(HttpStatus.NOT_FOUND, e.getError().getErrorCode(), e.getError().getErrorMessage());
    }

    @ExceptionHandler({RuntimeException.class, Exception.class})
    @ResponseBody
    public ResponseEntity<?> handlerAppException(Exception e) {
        return getExceptionResponse(HttpStatus.NOT_FOUND, Constant.INTERNAL_SERVER_ERROR, messageProperties.getMessage("INTERNAL_SERVER_ERROR"));
    }

    private ResponseEntity<ErrorResponse> getExceptionResponse(HttpStatus status, String errorCode, String errorMessage) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(errorCode, errorMessage), status);
    }
}
