package com.hospitalManagement.hospital_management.repositories;

import com.hospitalManagement.hospital_management.models.Appointment;
import com.hospitalManagement.hospital_management.service.MockDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Primary;
import java.util.List;

// @Repository - Disabled: Using Spring Data MongoDB auto-generated implementation
public class AppointmentRepositoryImpl implements AppointmentRepository {
    // DEPRECATED - This mock implementation is no longer used

    @Autowired
    private MockDataStore mockDataStore;

    @Override
    public <S extends Appointment> S save(S entity) {
        return (S) mockDataStore.saveAppointment(entity);
    }

    @Override
    public <S extends Appointment> Iterable<S> saveAll(Iterable<S> entities) {
        entities.forEach(this::save);
        return entities;
    }

    @Override
    public java.util.Optional<Appointment> findById(String s) {
        return mockDataStore.getAppointmentById(s);
    }

    @Override
    public boolean existsById(String s) {
        return mockDataStore.getAppointmentById(s).isPresent();
    }

    @Override
    public Iterable<Appointment> findAll() {
        return mockDataStore.getAllAppointments();
    }

    @Override
    public Iterable<Appointment> findAllById(Iterable<String> strings) {
        return findAll();
    }

    @Override
    public long count() {
        return mockDataStore.getAllAppointments().size();
    }

    @Override
    public void deleteById(String s) {
        mockDataStore.deleteAppointment(s);
    }

    @Override
    public void delete(Appointment entity) {
        if (entity.getId() != null) {
            mockDataStore.deleteAppointment(entity.getId());
        }
    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {
        strings.forEach(this::deleteById);
    }

    @Override
    public void deleteAll(Iterable<? extends Appointment> entities) {
        entities.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        mockDataStore.clearAllData();
    }

    @Override
    public List<Appointment> findByPatientId(String patientId) {
        return mockDataStore.getAppointmentsByPatientId(patientId);
    }

    @Override
    public List<Appointment> findByDoctorId(String doctorId) {
        return mockDataStore.getAppointmentsByDoctorId(doctorId);
    }

    @Override
    public List<Appointment> findByStatus(String status) {
        return mockDataStore.getAppointmentsByStatus(status);
    }

    @Override
    public List<Appointment> findByAppointmentDate(String appointmentDate) {
        return mockDataStore.getAllAppointments().stream()
                .filter(a -> a.getAppointmentDate().equals(appointmentDate))
                .toList();
    }
}
