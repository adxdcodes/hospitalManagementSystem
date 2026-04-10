package com.hospitalManagement.hospital_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto {
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
}
