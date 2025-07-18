package com.example.projectbase.service;

import com.example.projectbase.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> findAll();
    UserDTO findById(Long id);
    UserDTO create(UserDTO userDTO);
    UserDTO update(Long id, UserDTO userDTO);
    void delete(Long id);
}
