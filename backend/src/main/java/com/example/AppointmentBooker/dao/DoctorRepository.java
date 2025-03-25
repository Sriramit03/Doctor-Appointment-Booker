package com.example.AppointmentBooker.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.AppointmentBooker.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer>{
    
    @Query("SELECT d.availableTimings FROM Doctor d WHERE d.id = :doctorId")
    String findAvailableTimingsById(@Param("doctorId") int doctorId);
}
