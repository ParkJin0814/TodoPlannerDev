package com.example.todoplannerdev.service;

import com.example.todoplannerdev.dto.PlanRequestDto;
import com.example.todoplannerdev.dto.PlanResponseDto;
import com.example.todoplannerdev.entity.Plan;
import com.example.todoplannerdev.entity.User;
import com.example.todoplannerdev.exception.PlannerException;
import com.example.todoplannerdev.repository.PlanRepository;
import com.example.todoplannerdev.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    @Override
    public PlanResponseDto createPlan(PlanRequestDto dto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
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
    public PlanResponseDto updatePlanContent(Long planId, String contents, Long userId) {
        Plan plan = planRepository.findById(planId).orElseThrow();

        if (!plan.getUser().getId().equals(userId)) {
            throw new PlannerException(HttpStatus.FORBIDDEN, "권한이 없습니다.");
        }

        plan.updateContents(contents);
        return new PlanResponseDto(plan);
    }

    @Override
    public void deletePlan(Long planId, Long userId) {
        Plan plan = planRepository.findById(planId).orElseThrow();

        if (!plan.getUser().getId().equals(userId)) {
            throw new PlannerException(HttpStatus.FORBIDDEN, "권한이 없습니다.");
        }

        planRepository.delete(plan);
    }
}
