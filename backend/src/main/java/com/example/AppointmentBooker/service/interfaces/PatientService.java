package com.example.AppointmentBooker.service.interfaces;

import java.util.List;

import com.example.AppointmentBooker.entity.Patient;
import com.example.AppointmentBooker.dto.LogInCredential;


public interface PatientService {
    
    List<Patient> findAll();

    Patient findById(int id);

    Patient registerPatient(Patient patient);

    void deletePatient(int id);

    Patient findByMobileNo(String no);

    Patient update(Patient p);

    Patient authenticatePatient(LogInCredential detail);

}
