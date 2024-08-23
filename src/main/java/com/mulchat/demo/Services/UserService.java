package com.mulchat.demo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulchat.demo.Entities.Users;
import com.mulchat.demo.Repositories.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepository;


    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<Users> getUserById(Long id) {
        return userRepository.findById(id);
    }
public List<Users> createUsers(List<Users> users) {
    return userRepository.saveAll(users);
}

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}