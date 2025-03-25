package com.example.AppointmentBooker.dao;

import org.springframework.stereotype.Repository;

import com.example.AppointmentBooker.entity.Appointment;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;



@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment>  findByPatientId(int patientId,Sort sort);

    List<Appointment> findByDoctorId(int doctorId);
  
    

}
