package com.example.todoplannerdev.service;

import com.example.todoplannerdev.dto.PlanRequestDto;
import com.example.todoplannerdev.dto.PlanResponseDto;

public interface PlanService {
    PlanResponseDto createPlan(PlanRequestDto dto);

    PlanResponseDto findPlanById(Long planId);

    PlanResponseDto updatePlanContent(Long planId, String contents);

    void deletePlan(Long planId);
}
