package com.example.AppointmentBooker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.AppointmentBooker.dao.PatientRepository;
import com.example.AppointmentBooker.entity.Patient;
import com.example.AppointmentBooker.exception.AuthenticationFailureException;
import com.example.AppointmentBooker.exception.PatientsNotFoundException;
import com.example.AppointmentBooker.exception.UserAlreadyPresentException;
import com.example.AppointmentBooker.exception.UserNotFoundException;
import com.example.AppointmentBooker.service.interfaces.PatientService;
import com.example.AppointmentBooker.dto.LogInCredential;

import jakarta.transaction.Transactional;

@Service
public class PatientServiceImply implements PatientService {

    private PatientRepository patientRepository;
    private PasswordEncoder passwordEncoder;

    public PatientServiceImply(PatientRepository patientRepository, PasswordEncoder passwordEncoder) {
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> thePatients = patientRepository.findAll();
        if (thePatients.isEmpty())
            throw new PatientsNotFoundException("No Patients Registered Yet !");
        return thePatients;
    }

    @Override
    public Patient findById(int id) {
        Optional<Patient> result = patientRepository.findById(id);

        Patient thePatient = null;

        if (result.isPresent()) {
            thePatient = result.get();
        } else {
            // we didn't find the patient
            throw new UserNotFoundException("Patient with id - " + id + " is not present ");
        }

        return thePatient;
    }

    @Override
    @Transactional
    public Patient registerPatient(Patient thePatient) {
        Patient result = patientRepository.findByMobileNo(thePatient.getMobileNo());
        if (result != null) {
            throw new UserAlreadyPresentException("Patient With " + thePatient.getMobileNo() + " is Already Present !");
        }
        String encodedPassword = passwordEncoder.encode(thePatient.getPassword());
        thePatient.setPassword(encodedPassword);
        result = patientRepository.save(thePatient);
        return result;

    }

    @Override
    @Transactional
    public Patient update(Patient updatedPatient) {
        Patient existingPatient = patientRepository.findByMobileNo(updatedPatient.getMobileNo());
        if (existingPatient == null)
            throw new PatientsNotFoundException("Patient with " + updatedPatient.getMobileNo() + " is not exist.");
        existingPatient.setName(updatedPatient.getName());
        existingPatient.setPlace(updatedPatient.getPlace());
        existingPatient.setAge(updatedPatient.getAge());
        return patientRepository.save(existingPatient);
    }

    @Override
    public Patient findByMobileNo(String no) {
        Patient result = patientRepository.findByMobileNo(no);

        Patient thePatient = null;

        if (result != null) {
            return thePatient;
        } else {
            // we didn't find the patient
            throw new UserNotFoundException("Patient with Mobile Number - " + no + " is not found");
        }

    }

    @Override
    public void deletePatient(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePatient'");
    }

    @Override
    public Patient authenticatePatient(LogInCredential detail) {
        Patient patient = patientRepository.findByMobileNo(detail.getMobNo());
        if (patient != null && passwordEncoder.matches(detail.getPassword(), patient.getPassword())) {
            patient.setPassword("");
            return patient;
        }
        else
           throw new AuthenticationFailureException("Mobile Number or Password is Invalid ! ");
    }

}
