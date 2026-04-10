package com.hospitalManagement.hospital_management.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Document(collection = "patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
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
    private boolean active;
    private Long createdAt;
    private Long updatedAt;
}
