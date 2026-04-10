package com.hospitalManagement.hospital_management.services;

import com.hospitalManagement.hospital_management.models.Appointment;
import com.hospitalManagement.hospital_management.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment bookAppointment(Appointment appointment) {
        appointment.setStatus("SCHEDULED");
        appointment.setCreatedAt(System.currentTimeMillis());
        appointment.setUpdatedAt(System.currentTimeMillis());
        return appointmentRepository.save(appointment);
    }

    public Optional<Appointment> getAppointmentById(String id) {
        return appointmentRepository.findById(id);
    }

    public List<Appointment> getAppointmentsByPatientId(String patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    public List<Appointment> getAppointmentsByDoctorId(String doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    public List<Appointment> getAppointmentsByStatus(String status) {
        return appointmentRepository.findByStatus(status);
    }

    public List<Appointment> getAppointmentsByDate(String date) {
        return appointmentRepository.findByAppointmentDate(date);
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        appointmentRepository.findAll().forEach(appointments::add);
        return appointments;
    }

    public Appointment rescheduleAppointment(String id, String newDate, String newTime) throws Exception {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if (!appointment.isPresent()) {
            throw new Exception("Appointment not found");
        }
        Appointment appt = appointment.get();
        appt.setAppointmentDate(newDate);
        appt.setAppointmentTime(newTime);
        appt.setStatus("RESCHEDULED");
        appt.setUpdatedAt(System.currentTimeMillis());
        return appointmentRepository.save(appt);
    }

    public void cancelAppointment(String id) throws Exception {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if (!appointment.isPresent()) {
            throw new Exception("Appointment not found");
        }
        Appointment appt = appointment.get();
        appt.setStatus("CANCELLED");
        appt.setUpdatedAt(System.currentTimeMillis());
        appointmentRepository.save(appt);
    }

    public void completeAppointment(String id) throws Exception {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if (!appointment.isPresent()) {
            throw new Exception("Appointment not found");
        }
        Appointment appt = appointment.get();
        appt.setStatus("COMPLETED");
        appt.setUpdatedAt(System.currentTimeMillis());
        appointmentRepository.save(appt);
    }
}
