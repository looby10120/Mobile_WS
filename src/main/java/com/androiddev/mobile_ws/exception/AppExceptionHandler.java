package com.androiddev.mobile_ws.exception;

import com.androiddev.mobile_ws.model.response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handlerAnyException(Exception ex, WebRequest request) {

        String errorMessageDesc = ex.getLocalizedMessage();

        if (errorMessageDesc == null) errorMessageDesc = ex.toString();
        ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDesc);

        return new ResponseEntity<>(
                errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(value = {NullPointerException.class, UserServiceException.class})
    public  ResponseEntity<Object> handlerSpecificException(Exception ex, WebRequest request) {

        String errorMessageDesc = ex.getLocalizedMessage();

        if (errorMessageDesc == null) errorMessageDesc = ex.toString();
        ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDesc);

        return new ResponseEntity<>(
                errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

}
