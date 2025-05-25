package com.example.todoplannerdev.service;

import com.example.todoplannerdev.dto.PlanRequestDto;
import com.example.todoplannerdev.dto.PlanResponseDto;
import com.example.todoplannerdev.entity.Plan;
import com.example.todoplannerdev.entity.User;
import com.example.todoplannerdev.exception.ForbiddenAccessException;
import com.example.todoplannerdev.exception.NotFoundException;
import com.example.todoplannerdev.repository.PlanRepository;
import com.example.todoplannerdev.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    @Override
    public PlanResponseDto createPlan(PlanRequestDto dto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        Plan plan = new Plan(dto.getTitle(), dto.getContents(), user);
        user.addPlan(plan);
        planRepository.save(plan);

        return new PlanResponseDto(plan);
    }

    @Override
    public PlanResponseDto findPlanById(Long planId) {
        Plan plan = planRepository.findById(planId).orElseThrow(NotFoundException::new);


        return new PlanResponseDto(plan);
    }

    @Transactional
    @Override
    public PlanResponseDto updatePlanContent(Long planId, String contents, Long userId) {
        Plan plan = planRepository.findById(planId).orElseThrow(NotFoundException::new);

        if (!plan.getUser().getId().equals(userId)) {
            throw new ForbiddenAccessException();
        }

        plan.updateContents(contents);
        return new PlanResponseDto(plan);
    }

    @Override
    public void deletePlan(Long planId, Long userId) {
        Plan plan = planRepository.findById(planId).orElseThrow(NotFoundException::new);

        if (!plan.getUser().getId().equals(userId)) {
            throw new ForbiddenAccessException();
        }

        planRepository.delete(plan);
    }

    @Override
    public Page<PlanResponseDto> getPlanList(PageRequest pageRequest) {
        Page<Plan> planPage = planRepository.findAllByOrderByUpdatedAtDesc(pageRequest);
        return planPage.map(PlanResponseDto::new);
    }
}
