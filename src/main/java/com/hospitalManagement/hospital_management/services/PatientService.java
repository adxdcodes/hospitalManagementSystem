package com.hospitalManagement.hospital_management.services;

import com.hospitalManagement.hospital_management.models.Patient;
import com.hospitalManagement.hospital_management.repositories.PatientRepository;
import com.hospitalManagement.hospital_management.dtos.PatientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient createPatient(Patient patient) {
        patient.setActive(true);
        patient.setCreatedAt(System.currentTimeMillis());
        patient.setUpdatedAt(System.currentTimeMillis());
        return patientRepository.save(patient);
    }

    public Optional<Patient> getPatientById(String id) {
        return patientRepository.findById(id);
    }

    public Optional<Patient> getPatientByUserId(String userId) {
        return patientRepository.findByUserId(userId);
    }

    public Optional<Patient> getPatientByEmail(String email) {
        return patientRepository.findByEmail(email);
    }

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        patientRepository.findAll().forEach(patients::add);
        return patients;
    }

    public List<Patient> getActivePatients() {
        return patientRepository.findByActive(true);
    }

    public Patient updatePatient(String id, Patient patient) throws Exception {
        Optional<Patient> existingPatient = patientRepository.findById(id);
        if (!existingPatient.isPresent()) {
            throw new Exception("Patient not found");
        }
        patient.setId(id);
        patient.setUpdatedAt(System.currentTimeMillis());
        return patientRepository.save(patient);
    }

    public void deletePatient(String id) {
        patientRepository.deleteById(id);
    }

    public void deactivatePatient(String id) throws Exception {
        Optional<Patient> patient = patientRepository.findById(id);
        if (!patient.isPresent()) {
            throw new Exception("Patient not found");
        }
        Patient p = patient.get();
        p.setActive(false);
        p.setUpdatedAt(System.currentTimeMillis());
        patientRepository.save(p);
    }
}
