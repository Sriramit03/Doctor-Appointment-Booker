package com.example.AppointmentBooker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AppointmentBooker.entity.Doctor;
import com.example.AppointmentBooker.service.DoctorServiceImpl;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/doctors")
public class DoctorController {
    
    DoctorServiceImpl ds;

    public DoctorController(DoctorServiceImpl ds){
        this.ds = ds;
    }

    @GetMapping("/list")
    public List<Doctor> getALlDoctors() {
        return ds.findAll();
    }

    @GetMapping("/timings/{id}")
    public String getTimings(@PathVariable int id) {
        return ds.findAvailableTimingsById(id);
    }
    
    
}
