package com.hospitalManagement.hospital_management.repositories;

import com.hospitalManagement.hospital_management.models.Appointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, String> {
    List<Appointment> findByPatientId(String patientId);
    List<Appointment> findByDoctorId(String doctorId);
    List<Appointment> findByStatus(String status);
    List<Appointment> findByAppointmentDate(String appointmentDate);
}
