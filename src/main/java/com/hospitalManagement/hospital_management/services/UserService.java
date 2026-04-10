package com.hospitalManagement.hospital_management.services;

import com.hospitalManagement.hospital_management.models.User;
import com.hospitalManagement.hospital_management.repositories.UserRepository;
import com.hospitalManagement.hospital_management.dtos.LoginRequest;
import com.hospitalManagement.hospital_management.dtos.UserRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(UserRegisterRequest request) throws Exception {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new Exception("Email already registered");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // In production, use password hashing!
        user.setFullName(request.getFullName());
        user.setPhone(request.getPhone());
        user.setRole(request.getRole());
        user.setActive(true);
        user.setCreatedAt(System.currentTimeMillis());
        user.setUpdatedAt(System.currentTimeMillis());

        return userRepository.save(user);
    }

    public User login(LoginRequest request) throws Exception {
        Optional<User> user = userRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());
        if (!user.isPresent()) {
            throw new Exception("Invalid email or password");
        }
        return user.get();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User updateUser(String id, User user) throws Exception {
        Optional<User> existingUser = userRepository.findById(id);
        if (!existingUser.isPresent()) {
            throw new Exception("User not found");
        }
        user.setId(id);
        user.setUpdatedAt(System.currentTimeMillis());
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
