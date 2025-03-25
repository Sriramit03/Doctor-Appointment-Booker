package com.example.AppointmentBooker.exception;

public class UserAlreadyPresentException extends RuntimeException {
   
     public UserAlreadyPresentException(String msg){
          super(msg);
     }
}
