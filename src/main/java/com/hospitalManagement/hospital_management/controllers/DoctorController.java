package com.hospitalManagement.hospital_management.controllers;

import com.hospitalManagement.hospital_management.models.Doctor;
import com.hospitalManagement.hospital_management.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/register")
    public ResponseEntity<?> createDoctor(@RequestBody Doctor doctor) {
        try {
            Doctor savedDoctor = doctorService.createDoctor(doctor);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Doctor registered successfully");
            response.put("doctor", savedDoctor);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("success", "false");
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDoctor(@PathVariable String id) {
        try {
            Doctor doctor = doctorService.getDoctorById(id).orElseThrow(() -> new Exception("Doctor not found"));
            return ResponseEntity.ok(doctor);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("success", "false");
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getDoctorByUserId(@PathVariable String userId) {
        try {
            Doctor doctor = doctorService.getDoctorByUserId(userId).orElseThrow(() -> new Exception("Doctor not found"));
            return ResponseEntity.ok(doctor);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("success", "false");
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllDoctors() {
        try {
            List<Doctor> doctors = doctorService.getAllDoctors();
            return ResponseEntity.ok(doctors);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("success", "false");
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/active/all")
    public ResponseEntity<?> getActiveDoctors() {
        try {
            List<Doctor> doctors = doctorService.getActiveDoctors();
            return ResponseEntity.ok(doctors);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("success", "false");
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/specialization/{specialization}")
    public ResponseEntity<?> getDoctorsBySpecialization(@PathVariable String specialization) {
        try {
            List<Doctor> doctors = doctorService.getDoctorsBySpecialization(specialization);
            return ResponseEntity.ok(doctors);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("success", "false");
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<?> getDoctorsByDepartment(@PathVariable String department) {
        try {
            List<Doctor> doctors = doctorService.getDoctorsByDepartment(department);
            return ResponseEntity.ok(doctors);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("success", "false");
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable String id, @RequestBody Doctor doctor) {
        try {
            Doctor updatedDoctor = doctorService.updateDoctor(id, doctor);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Doctor updated successfully");
            response.put("doctor", updatedDoctor);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("success", "false");
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable String id) {
        try {
            doctorService.deleteDoctor(id);
            Map<String, String> response = new HashMap<>();
            response.put("success", "true");
            response.put("message", "Doctor deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("success", "false");
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}
