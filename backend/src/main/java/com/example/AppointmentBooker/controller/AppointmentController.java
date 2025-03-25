package com.example.AppointmentBooker.controller;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AppointmentBooker.service.AppointmentServiceImpl;
import com.example.AppointmentBooker.entity.Appointment;
import com.example.AppointmentBooker.exception.BadBodyValueException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private AppointmentServiceImpl appointmentService;

    public AppointmentController(AppointmentServiceImpl appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable int id) {
        return appointmentService.findById(id);
    }
    

    @GetMapping("/list")
    public List<Appointment> listAppointments() {
        return appointmentService.findAll();
    }

    @GetMapping("/doctor/{id}")
    public List<Appointment> getAppointmentByDoctorId(@PathVariable int id) {
        return appointmentService.findByDoctorId(id);
    }

    @GetMapping("/patient/{id}")
    public List<Appointment> getAppointmentByPatientId(@PathVariable int id) {
        return appointmentService.findByPatientId(id, Sort.by(Sort.Direction.DESC, "appointmentDate"));
    }

    @PostMapping
    public Appointment newAppointment(@RequestBody Appointment Ap) {
        Ap.setAppointmentId(0);
        return appointmentService.newAppointment(Ap);
    }

    @PutMapping
    public Appointment putAppointment(@RequestBody Appointment Ap) {
        if(Ap.getAppointmentId() == 0)
           throw new BadBodyValueException("Appointment Id is Invalid");
        return appointmentService.updateAppointment(Ap);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteAppointment(@PathVariable int id){
        return appointmentService.deleteAppointmentById(id);
    }

}
