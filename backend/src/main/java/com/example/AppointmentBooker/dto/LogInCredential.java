package com.example.AppointmentBooker.dto;

public class LogInCredential {

    private String mobNo;

    private String password;

    public LogInCredential() {

    }

    public String getMobNo() {
        return mobNo;
    }

    public LogInCredential(String mobNo, String password) {
        this.mobNo = mobNo;
        this.password = password;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
