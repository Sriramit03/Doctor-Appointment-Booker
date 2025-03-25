package com.example.AppointmentBooker.service.interfaces;

import java.util.List;

import com.example.AppointmentBooker.entity.Appointment;
import org.springframework.data.domain.Sort;

public interface AppointmentService {

  Appointment findById(int id);
    
  List<Appointment> findAll();

  List<Appointment> findByPatientId(int id,Sort sort);

  List<Appointment> findByDoctorId(int id);

  Appointment newAppointment(Appointment appointment);

  Appointment updateAppointment(Appointment appointment);

  Boolean deleteAppointmentById(int id);

}
