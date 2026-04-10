package com.hospitalManagement.hospital_management.service;

import com.hospitalManagement.hospital_management.models.*;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In-memory mock data store for development/testing when MongoDB is unavailable.
 * Data is stored in HashMaps and cleared on application restart.
 */
@Service
public class MockDataStore {
    private static final Map<String, User> users = new HashMap<>();
    private static final Map<String, Patient> patients = new HashMap<>();
    private static final Map<String, Doctor> doctors = new HashMap<>();
    private static final Map<String, Appointment> appointments = new HashMap<>();
    
    private static final AtomicLong userIdCounter = new AtomicLong(1);
    private static final AtomicLong patientIdCounter = new AtomicLong(1);
    private static final AtomicLong doctorIdCounter = new AtomicLong(1);
    private static final AtomicLong appointmentIdCounter = new AtomicLong(1);

    // === USER OPERATIONS ===
    public User saveUser(User user) {
        if (user.getId() == null || user.getId().isEmpty()) {
            user.setId(String.valueOf(userIdCounter.getAndIncrement()));
        }
        user.setCreatedAt(System.currentTimeMillis());
        user.setUpdatedAt(System.currentTimeMillis());
        users.put(user.getId(), user);
        return user;
    }

    public Optional<User> getUserById(String id) {
        return Optional.ofNullable(users.get(id));
    }

    public Optional<User> getUserByEmail(String email) {
        return users.values().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst();
    }

    public Optional<User> getUserByEmailAndPassword(String email, String password) {
        return users.values().stream()
                .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                .findFirst();
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public void deleteUser(String id) {
        users.remove(id);
    }

    // === PATIENT OPERATIONS ===
    public Patient savePatient(Patient patient) {
        if (patient.getId() == null || patient.getId().isEmpty()) {
            patient.setId(String.valueOf(patientIdCounter.getAndIncrement()));
        }
        patient.setCreatedAt(System.currentTimeMillis());
        if (patient.getMedicalHistory() == null) {
            patient.setMedicalHistory(new ArrayList<>());
        }
        patients.put(patient.getId(), patient);
        return patient;
    }

    public Optional<Patient> getPatientById(String id) {
        return Optional.ofNullable(patients.get(id));
    }

    public Optional<Patient> getPatientByUserId(String userId) {
        return patients.values().stream()
                .filter(p -> p.getUserId().equals(userId))
                .findFirst();
    }

    public Optional<Patient> getPatientByEmail(String email) {
        return patients.values().stream()
                .filter(p -> p.getEmail().equals(email))
                .findFirst();
    }

    public List<Patient> getActivePatients() {
        return patients.values().stream()
                .filter(Patient::isActive)
                .toList();
    }

    public List<Patient> getAllPatients() {
        return new ArrayList<>(patients.values());
    }

    public void deletePatient(String id) {
        patients.remove(id);
    }

    // === DOCTOR OPERATIONS ===
    public Doctor saveDoctor(Doctor doctor) {
        if (doctor.getId() == null || doctor.getId().isEmpty()) {
            doctor.setId(String.valueOf(doctorIdCounter.getAndIncrement()));
        }
        doctor.setCreatedAt(System.currentTimeMillis());
        if (doctor.getAvailableSlots() == null) {
            doctor.setAvailableSlots(new ArrayList<>());
        }
        doctors.put(doctor.getId(), doctor);
        return doctor;
    }

    public Optional<Doctor> getDoctorById(String id) {
        return Optional.ofNullable(doctors.get(id));
    }

    public Optional<Doctor> getDoctorByUserId(String userId) {
        return doctors.values().stream()
                .filter(d -> d.getUserId().equals(userId))
                .findFirst();
    }

    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return doctors.values().stream()
                .filter(d -> d.getSpecialization().equalsIgnoreCase(specialization))
                .toList();
    }

    public List<Doctor> getDoctorsByDepartment(String department) {
        return doctors.values().stream()
                .filter(d -> d.getDepartment().equalsIgnoreCase(department))
                .toList();
    }

    public List<Doctor> getActiveDoctors() {
        return doctors.values().stream()
                .filter(Doctor::isActive)
                .toList();
    }

    public List<Doctor> getAllDoctors() {
        return new ArrayList<>(doctors.values());
    }

    public void deleteDoctor(String id) {
        doctors.remove(id);
    }

    // === APPOINTMENT OPERATIONS ===
    public Appointment saveAppointment(Appointment appointment) {
        if (appointment.getId() == null || appointment.getId().isEmpty()) {
            appointment.setId(String.valueOf(appointmentIdCounter.getAndIncrement()));
        }
        appointment.setCreatedAt(System.currentTimeMillis());
        appointment.setUpdatedAt(System.currentTimeMillis());
        appointments.put(appointment.getId(), appointment);
        return appointment;
    }

    public Optional<Appointment> getAppointmentById(String id) {
        return Optional.ofNullable(appointments.get(id));
    }

    public List<Appointment> getAppointmentsByPatientId(String patientId) {
        return appointments.values().stream()
                .filter(a -> a.getPatientId().equals(patientId))
                .toList();
    }

    public List<Appointment> getAppointmentsByDoctorId(String doctorId) {
        return appointments.values().stream()
                .filter(a -> a.getDoctorId().equals(doctorId))
                .toList();
    }

    public List<Appointment> getAppointmentsByStatus(String status) {
        return appointments.values().stream()
                .filter(a -> a.getStatus().equals(status))
                .toList();
    }

    public List<Appointment> getAllAppointments() {
        return new ArrayList<>(appointments.values());
    }

    public void deleteAppointment(String id) {
        appointments.remove(id);
    }

    // === UTILITY ===
    public void clearAllData() {
        users.clear();
        patients.clear();
        doctors.clear();
        appointments.clear();
    }

    public Map<String, Object> getDataStats() {
        return Map.of(
                "users", users.size(),
                "patients", patients.size(),
                "doctors", doctors.size(),
                "appointments", appointments.size()
        );
    }
}
