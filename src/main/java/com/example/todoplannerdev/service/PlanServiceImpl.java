package com.example.todoplannerdev.service;

import com.example.todoplannerdev.dto.PlanRequestDto;
import com.example.todoplannerdev.dto.PlanResponseDto;
import com.example.todoplannerdev.entity.Plan;
import com.example.todoplannerdev.entity.User;
import com.example.todoplannerdev.repository.PlanRepository;
import com.example.todoplannerdev.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    @Override
    public PlanResponseDto createPlan(PlanRequestDto dto) {
        // 임시 유저
        User user = userRepository.findById(1L).orElseThrow();
        Plan plan = new Plan(dto.getTitle(), dto.getContents(), user);
        user.addPlan(plan);
        planRepository.save(plan);

        return new PlanResponseDto(plan);
    }

    @Override
    public PlanResponseDto findPlanById(Long planId) {
        Plan plan = planRepository.findById(planId).orElseThrow();
        return new PlanResponseDto(plan);
    }

    @Transactional
    @Override
    public PlanResponseDto updatePlanContent(Long planId, String contents) {
        Plan plan = planRepository.findById(planId).orElseThrow();

        plan.updateContents(contents);
        return new PlanResponseDto(plan);
    }

    @Override
    public void deletePlan(Long planId) {
        Plan plan = planRepository.findById(planId).orElseThrow();

        planRepository.delete(plan);
    }
}
