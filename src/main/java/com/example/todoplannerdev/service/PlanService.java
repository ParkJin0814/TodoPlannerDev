package com.example.todoplannerdev.service;

import com.example.todoplannerdev.dto.PlanRequestDto;
import com.example.todoplannerdev.dto.PlanResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface PlanService {
    PlanResponseDto createPlan(PlanRequestDto dto, Long userId);

    PlanResponseDto findPlanById(Long planId);

    PlanResponseDto updatePlanContent(Long planId, String contents, Long userId);

    void deletePlan(Long planId, Long userId);

    Page<PlanResponseDto> getPlanList(PageRequest pageRequest);
}
