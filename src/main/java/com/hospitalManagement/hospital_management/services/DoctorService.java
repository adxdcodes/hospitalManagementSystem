package com.hospitalManagement.hospital_management.services;

import com.hospitalManagement.hospital_management.models.Doctor;
import com.hospitalManagement.hospital_management.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor createDoctor(Doctor doctor) {
        doctor.setActive(true);
        doctor.setCreatedAt(System.currentTimeMillis());
        doctor.setUpdatedAt(System.currentTimeMillis());
        return doctorRepository.save(doctor);
    }

    public Optional<Doctor> getDoctorById(String id) {
        return doctorRepository.findById(id);
    }

    public Optional<Doctor> getDoctorByUserId(String userId) {
        return doctorRepository.findByUserId(userId);
    }

    public Optional<Doctor> getDoctorByEmail(String email) {
        return doctorRepository.findByEmail(email);
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        doctorRepository.findAll().forEach(doctors::add);
        return doctors;
    }

    public List<Doctor> getActiveDoctors() {
        return doctorRepository.findByActive(true);
    }

    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return doctorRepository.findBySpecialization(specialization);
    }

    public List<Doctor> getDoctorsByDepartment(String department) {
        return doctorRepository.findByDepartment(department);
    }

    public Doctor updateDoctor(String id, Doctor doctor) throws Exception {
        Optional<Doctor> existingDoctor = doctorRepository.findById(id);
        if (!existingDoctor.isPresent()) {
            throw new Exception("Doctor not found");
        }
        doctor.setId(id);
        doctor.setUpdatedAt(System.currentTimeMillis());
        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(String id) {
        doctorRepository.deleteById(id);
    }

    public void deactivateDoctor(String id) throws Exception {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        if (!doctor.isPresent()) {
            throw new Exception("Doctor not found");
        }
        Doctor d = doctor.get();
        d.setActive(false);
        d.setUpdatedAt(System.currentTimeMillis());
        doctorRepository.save(d);
    }
}
