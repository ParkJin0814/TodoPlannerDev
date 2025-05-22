package com.example.todoplannerdev.repository;

import com.example.todoplannerdev.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}
