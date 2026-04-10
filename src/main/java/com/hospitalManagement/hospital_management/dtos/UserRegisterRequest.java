package com.hospitalManagement.hospital_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest {
    private String email;
    private String password;
    private String fullName;
    private String phone;
    private String role; // PATIENT or DOCTOR
}
