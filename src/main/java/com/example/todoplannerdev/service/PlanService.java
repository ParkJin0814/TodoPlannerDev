package com.example.todoplannerdev.service;

import com.example.todoplannerdev.dto.PlanRequestDto;
import com.example.todoplannerdev.dto.PlanResponseDto;

public interface PlanService {
    PlanResponseDto createPlan(PlanRequestDto dto, Long userId);

    PlanResponseDto findPlanById(Long planId);

    PlanResponseDto updatePlanContent(Long planId, String contents, Long userId);

    void deletePlan(Long planId, Long userId);
}
