package com.example.AppointmentBooker.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message){
        super(message);
    }
    
}
