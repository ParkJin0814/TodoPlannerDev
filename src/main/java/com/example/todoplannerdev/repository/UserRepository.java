package com.example.todoplannerdev.repository;

import com.example.todoplannerdev.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
