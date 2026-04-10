package com.hospitalManagement.hospital_management.repositories;

import com.hospitalManagement.hospital_management.models.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface PatientRepository extends CrudRepository<Patient, String> {
    Optional<Patient> findByUserId(String userId);
    Optional<Patient> findByEmail(String email);
    List<Patient> findByActive(boolean active);
}
