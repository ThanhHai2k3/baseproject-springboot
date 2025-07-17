package com.example.projectbase.repository;

import com.example.projectbase.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}