package com.hospitalManagement.hospital_management.repositories;

import com.hospitalManagement.hospital_management.models.Doctor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, String> {
    Optional<Doctor> findByUserId(String userId);
    Optional<Doctor> findByEmail(String email);
    List<Doctor> findBySpecialization(String specialization);
    List<Doctor> findByDepartment(String department);
    List<Doctor> findByActive(boolean active);
}
