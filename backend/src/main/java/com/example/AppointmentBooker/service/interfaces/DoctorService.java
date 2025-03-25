package com.example.AppointmentBooker.service.interfaces;

import java.util.List;

import com.example.AppointmentBooker.entity.Doctor;

public interface DoctorService {
    
    List<Doctor> findAll();
    String findAvailableTimingsById(int id);
}
