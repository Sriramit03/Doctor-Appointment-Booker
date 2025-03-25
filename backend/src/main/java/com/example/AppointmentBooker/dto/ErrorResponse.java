package com.example.AppointmentBooker.dto;

import java.util.Date;

public class ErrorResponse {

    private int status;
    private String message;
    private Date timestamp;

    public ErrorResponse(int status, String message, Date date) {
        this.status = status;
        this.message = message;
        this.timestamp = date;
    }

    // Getters and Setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
