package com.example.AppointmentBooker.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AppointmentBooker.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {
    
    Patient findByMobileNo(String no);
}
