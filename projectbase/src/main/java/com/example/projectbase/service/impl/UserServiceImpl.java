package com.example.projectbase.service.impl;

import com.example.projectbase.dto.UserDTO;
import com.example.projectbase.entity.User;
import com.example.projectbase.repository.UserRepository;
import com.example.projectbase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
    @Cacheable(value = "users")
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "user", key = "#id")
    public UserDTO findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return toDTO(optionalUser.get());
        }
        throw new RuntimeException("User not found with id: " + id);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "users", allEntries = true),
            @CacheEvict(value = "user", key = "#result.id")
    })
    public UserDTO create(UserDTO userDTO) {
        User saved = userRepository.save(toEntity(userDTO));
        return toDTO(saved);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "users", allEntries = true),
            @CacheEvict(value = "user", key = "#id")
    })
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
    @Caching(evict = {
            @CacheEvict(value = "users", allEntries = true),
            @CacheEvict(value = "user", key = "#id")
    })
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
