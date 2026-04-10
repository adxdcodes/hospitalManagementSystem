package com.hospitalManagement.hospital_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {
    private String id;
    private String userId;
    private String email;
    private String fullName;
    private String phone;
    private String gender;
    private String dateOfBirth;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String bloodGroup;
    private String allergies;
    private List<String> medicalHistory;
}
