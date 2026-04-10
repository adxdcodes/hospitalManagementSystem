package com.hospitalManagement.hospital_management.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Document(collection = "doctors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    private String id;
    private String userId;
    private String email;
    private String fullName;
    private String phone;
    private String specialization;
    private String department;
    private String licenseNumber;
    private String experience;
    private double consultationFee;
    private List<String> availableSlots;
    private String consultationHours;
    private String address;
    private String city;
    private boolean active;
    private Long createdAt;
    private Long updatedAt;
}
