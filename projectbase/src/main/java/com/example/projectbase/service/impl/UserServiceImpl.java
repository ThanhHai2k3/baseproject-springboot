package com.example.projectbase.service.impl;

import com.example.projectbase.dto.UserDTO;
import com.example.projectbase.entity.User;
import com.example.projectbase.repository.UserRepository;
import com.example.projectbase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private UserDTO toDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail());
    }

    private User toEntity(UserDTO dto) {
        return new User(dto.getId(), dto.getName(), dto.getEmail());
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return toDTO(optionalUser.get());
        }
        throw new RuntimeException("User not found with id: " + id);
    }

    @Override
    public UserDTO create(UserDTO userDTO) {
        User saved = userRepository.save(toEntity(userDTO));
        return toDTO(saved);
    }

    @Override
    public UserDTO update(Long id, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found with id: " + id);
        }

        User existing = optionalUser.get();
        existing.setName(userDTO.getName());
        existing.setEmail(userDTO.getEmail());
        return toDTO(userRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
