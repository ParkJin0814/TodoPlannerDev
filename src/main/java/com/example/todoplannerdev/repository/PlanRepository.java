package com.example.todoplannerdev.repository;

import com.example.todoplannerdev.entity.Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    Page<Plan> findAllByOrderByUpdatedAtDesc(PageRequest pageRequest);
}
