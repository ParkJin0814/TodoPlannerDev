package com.example.todoplannerdev.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Plan {
    @Id
    private Long id;
}
