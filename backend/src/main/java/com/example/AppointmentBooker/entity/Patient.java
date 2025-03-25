package com.example.AppointmentBooker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "patients")
public class Patient {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "place")
    private String place;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "age")
    private int age;

    @Column(name = "password")
    private String password;

/*     @OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
    private List<Appointment> appointments; */

    public Patient() {
    }

    public Patient(int id, String name, String place, String mobileNo, String password, int age) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.mobileNo = mobileNo;
        this.password = password;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getMobileNo() {
        return this.mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Patient [id=" + id + ", name=" + name + ", place=" + place + ", mobileNo=" + mobileNo + ", age=" + age
                + "]";
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*
     * public List<Appointment> getAppointments() {
     * return appointments;
     * }
     * 
     * public void setAppointments(List<Appointment> appointments) {
     * this.appointments = appointments;
     * }
     */

}
