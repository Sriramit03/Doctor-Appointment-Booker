package com.example.AppointmentBooker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctors")
public class Doctor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "specialization")
    private String specialization;

    @Column( name = "available_timings")
    private String availableTimings;

    @Column(name = "experience")
    private String experience;

    @Column(name = "rating")
    private float rating;


/*     @OneToMany(fetch =FetchType.LAZY ,mappedBy = "doctor")
    private List<Appointment> appointments; */

    public Doctor() {
    }

    
    public Doctor(int doctorId, String doctorName, String specialization, String availableTimings, String experience, float rating) {
        this.id = doctorId;
        this.name = doctorName;
        this.specialization = specialization;
        this.availableTimings = availableTimings;
        this.experience = experience;
        this.rating = rating;
    }
    
    public int getDoctorId() {
        return id;
    }

    public void setDoctorId(int doctorId) {
        this.id = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String doctorName) {
        this.name = doctorName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getAvailableTimings() {
        return availableTimings;
    }

    public void setAvailableTimings(String availableTimings) {
        this.availableTimings = availableTimings;
    }


    
    
    public String getExperience() {
        return experience;
    }
    
    
    public void setExperience(String experience) {
        this.experience = experience;
    }
    
    
    public float getRating() {
        return rating;
    }


    public void setRating(float rating) {
        this.rating = rating;
    }
    
    
    @Override
    public String toString() {
        return "Doctor [doctorId=" + id + ", doctorName=" + name + ", specialization=" + specialization
        + ", availableTimings=" + availableTimings + "]";
    }

    /* public List<Appointment> getAppointments() {
        return appointments;
    }
    
    
    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    } */
    
    
    
}
