package com.example.AppointmentBooker.exception;


public class AuthenticationFailureException extends RuntimeException {
 
    public AuthenticationFailureException(String msg){
        super(msg);
    }
    
}