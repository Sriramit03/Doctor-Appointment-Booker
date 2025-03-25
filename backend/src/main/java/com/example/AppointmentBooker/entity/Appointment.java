package com.example.AppointmentBooker.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int appointmentId;

    @Column(name = "doctor_id")
    private int doctorId;

    @Column(name = "patient_id")
    private int patientId;

    @Column(name = "appointment_date")
    private String appointmentDate;

    @Column(name = "appointment_time")
    private String appointmentTime;

    @Column(name = "complaint")
    private String complains;

    @Column(name = "status")
    private String status;

/*     @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name = "patient_id", nullable = false, insertable = false, updatable = false)
    private Patient patient;
 */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name = "doctor_id", nullable = false, insertable = false, updatable = false)
    private Doctor doctor;

    public Appointment() {
    }

    public Appointment(int doctorId, int patientId, String appointmentDate, String appointmentTime,
            String complains, String status) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.complains = complains;
        this.status = status;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public void setComplains(String complains) {
        this.complains = complains;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public String getComplains() {
        return complains;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Appointment [appointmentId=" + appointmentId + ", doctorId=" + doctorId + ", patientId=" + patientId
                + ", appointmentDate=" + appointmentDate + ", appointmentTime=" + appointmentTime + ", complains="
                + complains +  "]";
    }

    
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    
    public Doctor getDoctor() {
        return doctor;
    }

/*     public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    } */


}
