package com.example.projectbase.service;

import com.example.projectbase.entity.User;
import com.example.projectbase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Cacheable(value = "users")
    public List<User> getAllUsers() {
        System.out.println("Fetching users from DB...");
        return userRepository.findAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @CacheEvict(value = "users", allEntries = true)
    public User updateUser(Long id, User userData) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(userData.getName());
            user.setEmail(userData.getEmail());
            return userRepository.save(user);
        }
        return null;
    }

    @CacheEvict(value = "users", allEntries = true)
    public User patchUser(Long id, User userData) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (userData.getName() != null)
                user.setName(userData.getName());
            if (userData.getEmail() != null)
                user.setEmail(userData.getEmail());
            return userRepository.save(user);
        }
        return null;
    }
}
