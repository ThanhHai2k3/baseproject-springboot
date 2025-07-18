package com.example.projectbase.controller;

import com.example.projectbase.dto.UserDTO;
import com.example.projectbase.dto.response.ApiResponse;
import com.example.projectbase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDTO>>> getAll() {
        List<UserDTO> users = userService.findAll();
        ApiResponse<List<UserDTO>> response = new ApiResponse<>(true, "Get all users successfully", users);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> getById(@PathVariable Long id) {
        UserDTO user = userService.findById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "User found", user));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserDTO>> create(@RequestBody UserDTO dto) {
        UserDTO created = userService.create(dto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, "User created", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> update(@PathVariable Long id, @RequestBody UserDTO dto) {
        UserDTO updated = userService.update(id, dto);
        return ResponseEntity.ok(new ApiResponse<>(true, "User updated", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "User deleted", null));
    }
}

