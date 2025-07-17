package com.example.projectbase.controller;

import com.example.projectbase.entity.User;
import com.example.projectbase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers(); // CÓ CACHE
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user); // CLEAR CACHE
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user); // CLEAR CACHE
    }

    @PatchMapping("/{id}")
    public User patchUser(@PathVariable Long id, @RequestBody User user) {
        return userService.patchUser(id, user); // CLEAR CACHE
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id); // CLEAR CACHE
    }
}