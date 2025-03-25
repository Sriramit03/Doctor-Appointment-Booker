package com.example.AppointmentBooker.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.AppointmentBooker.dao.AppointmentRepository;
import com.example.AppointmentBooker.entity.Appointment;
import com.example.AppointmentBooker.exception.PatientsNotFoundException;
import com.example.AppointmentBooker.exception.UserNotFoundException;
import com.example.AppointmentBooker.service.interfaces.AppointmentService;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

  private AppointmentRepository ar;

  public AppointmentServiceImpl(AppointmentRepository appointmentRepository, EntityManager theEM) {
    ar = appointmentRepository;
  }

  @Override
  public List<Appointment> findAll() {
    List<Appointment> result = ar.findAll();
    if (result.isEmpty())
      throw new PatientsNotFoundException("No Patients Registered Yet !");

    return result;
  }

  @Override
  public List<Appointment> findByPatientId(int id, Sort sort) {
    List<Appointment> result = ar.findByPatientId(id, sort);
    if (result.isEmpty())
      throw new PatientsNotFoundException("No Registered Entry !");
    return result;

  }

  @Override
  public List<Appointment> findByDoctorId(int id) {
    List<Appointment> result = ar.findByDoctorId(id);
    if (result.isEmpty())
      throw new PatientsNotFoundException("No Patients Registered for this Doctor !");
    return result;
  }

  @Override
  @Transactional
  public Appointment newAppointment(Appointment ap) {
    Appointment res = ar.save(ap);
    return res;

  }

  @Override
  @Transactional
  public Appointment updateAppointment(Appointment appointment) {
    Optional<Appointment> theAppointment = ar.findById(appointment.getAppointmentId());
    Appointment oldAppointment = theAppointment.get();
    if (oldAppointment == null)
      throw new UserNotFoundException("Appointment Not Found! ");
    oldAppointment.setAppointmentDate(appointment.getAppointmentDate());
    oldAppointment.setAppointmentTime(appointment.getAppointmentTime());
    oldAppointment.setComplains(appointment.getComplains());
    return ar.save(oldAppointment);
  }

  @Override
  @Transactional
  public Boolean deleteAppointmentById(int id) {
    ar.deleteById(id);
    if (ar.findById(id).isPresent())
      throw new RuntimeException("Something Went Wrong!");
    return true;
  }

  @Override
  public Appointment findById(int id) {
    Optional<Appointment> res = ar.findById(id);
    Appointment theAppointment = res.get();
    if (theAppointment == null) {
      throw new UserNotFoundException("Appointment Not Found!");
    }
    return theAppointment;
  }

}
