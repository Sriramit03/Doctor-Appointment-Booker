package com.example.AppointmentBooker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AppointmentBooker.entity.Patient;
import com.example.AppointmentBooker.service.PatientServiceImply;
import com.example.AppointmentBooker.dto.LogInCredential;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/patients")
public class PatientController {

    private PatientServiceImply patientServiceImply;

    public PatientController(PatientServiceImply patientServiceImply){
        this.patientServiceImply = patientServiceImply;
    }
    
    @GetMapping("/{theId}")
    public Patient getPatientById(@PathVariable int theId) {
        return patientServiceImply.findById(theId);
    }

    @GetMapping("/list")
    public List<Patient> getAllPatient() {
        return patientServiceImply.findAll();
    }

    @GetMapping("/mobileNo/{mobNo}")
    public Patient getPatientByMobileNo(@PathVariable String mobNo){
        return patientServiceImply.findByMobileNo(mobNo);
    }
    
    @PostMapping
    public Patient postNewPatient(@RequestBody Patient thePatient) {
        thePatient.setId(0);
        return patientServiceImply.registerPatient(thePatient);
    }

    @PutMapping
    public Patient updatePatient(@RequestBody Patient thePatient){
       return patientServiceImply.update(thePatient);
    }
    
    @PostMapping("/logIn")
    public Patient authenticateUser(@RequestBody LogInCredential credential ){
        return patientServiceImply.authenticatePatient(credential);
    }

}
