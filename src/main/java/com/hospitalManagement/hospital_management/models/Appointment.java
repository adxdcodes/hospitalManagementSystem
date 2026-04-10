package com.hospitalManagement.hospital_management.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "appointments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    private String id;
    private String patientId;
    private String doctorId;
    private String appointmentDate;
    private String appointmentTime;
    private String status; // SCHEDULED, COMPLETED, CANCELLED, RESCHEDULED
    private String reason;
    private String notes;
    private String department;
    private double consultationFee;
    private boolean reminded;
    private Long createdAt;
    private Long updatedAt;
}
