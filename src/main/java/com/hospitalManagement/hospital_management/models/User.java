package com.hospitalManagement.hospital_management.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;
    private String email;
    private String password;
    private String fullName;
    private String phone;
    private String role; // PATIENT, DOCTOR, ADMIN
    private boolean active;
    private Long createdAt;
    private Long updatedAt;
}
