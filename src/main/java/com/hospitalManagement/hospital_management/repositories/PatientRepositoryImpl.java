package com.hospitalManagement.hospital_management.repositories;

import com.hospitalManagement.hospital_management.models.Patient;
import com.hospitalManagement.hospital_management.service.MockDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Primary;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

// @Repository - Disabled: Using Spring Data MongoDB auto-generated implementation
public class PatientRepositoryImpl implements PatientRepository {
    // DEPRECATED - This mock implementation is no longer used

    @Autowired
    private MockDataStore mockDataStore;

    @Override
    public <S extends Patient> S save(S entity) {
        return (S) mockDataStore.savePatient(entity);
    }

    @Override
    public <S extends Patient> Iterable<S> saveAll(Iterable<S> entities) {
        entities.forEach(this::save);
        return entities;
    }

    @Override
    public Optional<Patient> findById(String s) {
        return mockDataStore.getPatientById(s);
    }

    @Override
    public boolean existsById(String s) {
        return mockDataStore.getPatientById(s).isPresent();
    }

    @Override
    public Iterable<Patient> findAll() {
        return mockDataStore.getAllPatients();
    }

    @Override
    public Iterable<Patient> findAllById(Iterable<String> strings) {
        return findAll();
    }

    @Override
    public long count() {
        return mockDataStore.getAllPatients().size();
    }

    @Override
    public void deleteById(String s) {
        mockDataStore.deletePatient(s);
    }

    @Override
    public void delete(Patient entity) {
        if (entity.getId() != null) {
            mockDataStore.deletePatient(entity.getId());
        }
    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {
        strings.forEach(this::deleteById);
    }

    @Override
    public void deleteAll(Iterable<? extends Patient> entities) {
        entities.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        mockDataStore.clearAllData();
    }

    @Override
    public Optional<Patient> findByUserId(String userId) {
        return mockDataStore.getPatientByUserId(userId);
    }

    @Override
    public Optional<Patient> findByEmail(String email) {
        return mockDataStore.getPatientByEmail(email);
    }

    @Override
    public List<Patient> findByActive(boolean active) {
        if (active) {
            return mockDataStore.getActivePatients();
        } else {
            List<Patient> allPatients = mockDataStore.getAllPatients();
            return allPatients.stream()
                    .filter(p -> !p.isActive())
                    .toList();
        }
    }
}
