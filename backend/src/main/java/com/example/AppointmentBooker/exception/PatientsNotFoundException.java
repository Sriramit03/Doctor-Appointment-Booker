package com.example.AppointmentBooker.exception;

public class PatientsNotFoundException  extends RuntimeException{

    public PatientsNotFoundException(String msg){
        super(msg);
    }
    
}
