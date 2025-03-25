package com.example.AppointmentBooker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.AppointmentBooker.dto.ErrorResponse;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ UserNotFoundException.class, PatientsNotFoundException.class })
    public ResponseEntity<ErrorResponse> handleCustomNotFoundException(Exception ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                new Date());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ UserAlreadyPresentException.class })
    public ResponseEntity<ErrorResponse> handleCustomAlreadyPresentException(UserAlreadyPresentException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                new Date());

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ AuthenticationFailureException.class })
    public ResponseEntity<ErrorResponse> handleAuthenticationFailureException(AuthenticationFailureException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                ex.getMessage(),
                new Date());

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({ BadBodyValueException.class })
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadBodyValueException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                new Date());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntime(RuntimeException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Server Side Error",
                new Date());
        return new ResponseEntity<>(error,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
