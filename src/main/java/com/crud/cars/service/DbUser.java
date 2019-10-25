package com.crud.cars.service;

import com.crud.cars.domain.User;
import com.crud.cars.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbUser {

    @Autowired
    private UserRepository repository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public Optional<User> getUser(final Long id) {
        return repository.findById(id);
    }

    public User saveUser(final User user) {
        return repository.save(user);
    }

    public void deleteByUserId(final Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
