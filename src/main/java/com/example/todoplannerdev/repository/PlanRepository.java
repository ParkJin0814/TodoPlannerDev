package com.example.todoplannerdev.repository;

import com.example.todoplannerdev.entity.Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    // 업 캐스팅을 통하여 데이터베이스 조회
    Page<Plan> findAllByOrderByUpdatedAtDesc(Pageable pageable);
}
