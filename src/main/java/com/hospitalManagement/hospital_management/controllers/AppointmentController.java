package com.hospitalManagement.hospital_management.controllers;

import com.hospitalManagement.hospital_management.models.Appointment;
import com.hospitalManagement.hospital_management.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/book")
    public ResponseEntity<?> bookAppointment(@RequestBody Appointment appointment) {
        try {
            Appointment bookedAppointment = appointmentService.bookAppointment(appointment);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Appointment booked successfully");
            response.put("appointment", bookedAppointment);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("success", "false");
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAppointment(@PathVariable String id) {
        try {
            Appointment appointment = appointmentService.getAppointmentById(id).orElseThrow(() -> new Exception("Appointment not found"));
            return ResponseEntity.ok(appointment);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("success", "false");
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<?> getAppointmentsByPatient(@PathVariable String patientId) {
        try {
            List<Appointment> appointments = appointmentService.getAppointmentsByPatientId(patientId);
            return ResponseEntity.ok(appointments);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("success", "false");
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<?> getAppointmentsByDoctor(@PathVariable String doctorId) {
        try {
            List<Appointment> appointments = appointmentService.getAppointmentsByDoctorId(doctorId);
            return ResponseEntity.ok(appointments);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("success", "false");
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> getAppointmentsByStatus(@PathVariable String status) {
        try {
            List<Appointment> appointments = appointmentService.getAppointmentsByStatus(status);
            return ResponseEntity.ok(appointments);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("success", "false");
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<?> getAppointmentsByDate(@PathVariable String date) {
        try {
            List<Appointment> appointments = appointmentService.getAppointmentsByDate(date);
            return ResponseEntity.ok(appointments);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("success", "false");
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAppointments() {
        try {
            List<Appointment> appointments = appointmentService.getAllAppointments();
            return ResponseEntity.ok(appointments);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("success", "false");
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PutMapping("/reschedule/{id}")
    public ResponseEntity<?> rescheduleAppointment(@PathVariable String id, @RequestBody Map<String, String> request) {
        try {
            String newDate = request.get("newDate");
            String newTime = request.get("newTime");
            Appointment updatedAppointment = appointmentService.rescheduleAppointment(id, newDate, newTime);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Appointment rescheduled successfully");
            response.put("appointment", updatedAppointment);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("success", "false");
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<?> cancelAppointment(@PathVariable String id) {
        try {
            appointmentService.cancelAppointment(id);
            Map<String, String> response = new HashMap<>();
            response.put("success", "true");
            response.put("message", "Appointment cancelled successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("success", "false");
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PutMapping("/complete/{id}")
    public ResponseEntity<?> completeAppointment(@PathVariable String id) {
        try {
            appointmentService.completeAppointment(id);
            Map<String, String> response = new HashMap<>();
            response.put("success", "true");
            response.put("message", "Appointment marked as completed");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("success", "false");
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}
