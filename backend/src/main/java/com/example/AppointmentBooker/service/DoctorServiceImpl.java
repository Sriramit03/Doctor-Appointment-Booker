package com.example.AppointmentBooker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.AppointmentBooker.dao.DoctorRepository;
import com.example.AppointmentBooker.entity.Doctor;
import com.example.AppointmentBooker.exception.UserNotFoundException;
import com.example.AppointmentBooker.service.interfaces.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {

    DoctorRepository dr ;

    public DoctorServiceImpl(DoctorRepository dr){
        this.dr = dr;
    }

    @Override
    public List<Doctor> findAll() {
        List<Doctor> doctors = dr.findAll();
        if(doctors.isEmpty())
           throw new UserNotFoundException("No Doctors Found !");
        return doctors;   
    }

    @Override
    public String findAvailableTimingsById(int id) { 
         return dr.findAvailableTimingsById(id);
    }
    
}
