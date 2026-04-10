package com.hospitalManagement.hospital_management.repositories;

import com.hospitalManagement.hospital_management.models.User;
import com.hospitalManagement.hospital_management.service.MockDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Primary;
import java.util.List;
import java.util.Optional;

// @Repository - Disabled: Using Spring Data MongoDB auto-generated implementation
public class UserRepositoryImpl implements UserRepository {
    // DEPRECATED - This mock implementation is no longer used

    @Autowired
    private MockDataStore mockDataStore;

    @Override
    public <S extends User> S save(S entity) {
        return (S) mockDataStore.saveUser(entity);
    }

    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
        entities.forEach(this::save);
        return entities;
    }

    @Override
    public Optional<User> findById(String s) {
        return mockDataStore.getUserById(s);
    }

    @Override
    public boolean existsById(String s) {
        return mockDataStore.getUserById(s).isPresent();
    }

    @Override
    public Iterable<User> findAll() {
        return mockDataStore.getAllUsers();
    }

    @Override
    public Iterable<User> findAllById(Iterable<String> strings) {
        return findAll();
    }

    @Override
    public long count() {
        return mockDataStore.getAllUsers().size();
    }

    @Override
    public void deleteById(String s) {
        mockDataStore.deleteUser(s);
    }

    @Override
    public void delete(User entity) {
        if (entity.getId() != null) {
            mockDataStore.deleteUser(entity.getId());
        }
    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {
        strings.forEach(this::deleteById);
    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {
        entities.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        mockDataStore.clearAllData();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return mockDataStore.getUserByEmail(email);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        return mockDataStore.getUserByEmailAndPassword(email, password);
    }
}
