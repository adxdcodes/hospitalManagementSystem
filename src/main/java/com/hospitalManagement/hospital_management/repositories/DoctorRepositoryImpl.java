package com.hospitalManagement.hospital_management.repositories;

import com.hospitalManagement.hospital_management.models.Doctor;
import com.hospitalManagement.hospital_management.service.MockDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Primary;
import java.util.Optional;
import java.util.List;

// @Repository - Disabled: Using Spring Data MongoDB auto-generated implementation
public class DoctorRepositoryImpl implements DoctorRepository {
    // DEPRECATED - This mock implementation is no longer used

    @Autowired
    private MockDataStore mockDataStore;

    @Override
    public <S extends Doctor> S save(S entity) {
        return (S) mockDataStore.saveDoctor(entity);
    }

    @Override
    public <S extends Doctor> Iterable<S> saveAll(Iterable<S> entities) {
        entities.forEach(this::save);
        return entities;
    }

    @Override
    public Optional<Doctor> findById(String s) {
        return mockDataStore.getDoctorById(s);
    }

    @Override
    public boolean existsById(String s) {
        return mockDataStore.getDoctorById(s).isPresent();
    }

    @Override
    public Iterable<Doctor> findAll() {
        return mockDataStore.getAllDoctors();
    }

    @Override
    public Iterable<Doctor> findAllById(Iterable<String> strings) {
        return findAll();
    }

    @Override
    public long count() {
        return mockDataStore.getAllDoctors().size();
    }

    @Override
    public void deleteById(String s) {
        mockDataStore.deleteDoctor(s);
    }

    @Override
    public void delete(Doctor entity) {
        if (entity.getId() != null) {
            mockDataStore.deleteDoctor(entity.getId());
        }
    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {
        strings.forEach(this::deleteById);
    }

    @Override
    public void deleteAll(Iterable<? extends Doctor> entities) {
        entities.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        mockDataStore.clearAllData();
    }

    @Override
    public Optional<Doctor> findByUserId(String userId) {
        return mockDataStore.getDoctorByUserId(userId);
    }

    @Override
    public Optional<Doctor> findByEmail(String email) {
        return mockDataStore.getAllDoctors().stream()
                .filter(d -> d.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public List<Doctor> findBySpecialization(String specialization) {
        return mockDataStore.getDoctorsBySpecialization(specialization);
    }

    @Override
    public List<Doctor> findByDepartment(String department) {
        return mockDataStore.getDoctorsByDepartment(department);
    }

    @Override
    public List<Doctor> findByActive(boolean active) {
        if (active) {
            return mockDataStore.getActiveDoctors();
        } else {
            List<Doctor> allDoctors = mockDataStore.getAllDoctors();
            return allDoctors.stream()
                    .filter(d -> !d.isActive())
                    .toList();
        }
    }
}
