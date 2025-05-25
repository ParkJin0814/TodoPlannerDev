package com.example.todoplannerdev.controller;

import com.example.todoplannerdev.common.LoginConst;
import com.example.todoplannerdev.dto.*;
import com.example.todoplannerdev.service.PlanService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/plan")
public class PlanController {
    private final PlanService planService;

    // 일정생성
    @PostMapping
    public ResponseEntity<PlanResponseDto> createPlan(@RequestBody PlanRequestDto dto, HttpServletRequest request) {

        HttpSession session = request.getSession();

        LoginResponseDto loginUser = (LoginResponseDto)session.getAttribute(LoginConst.LOGIN_USER);
        Long userId = loginUser.getId();

        return new ResponseEntity<>(planService.createPlan(dto, userId), HttpStatus.CREATED);
    }

    // 고유식별자로 단건조회
    @GetMapping("/{planId}")
    public ResponseEntity<PlanResponseDto> findPlanById(@PathVariable Long planId) {
        return new ResponseEntity<>(planService.findPlanById(planId), HttpStatus.OK);
    }

    // 내용수정
    @PutMapping("/{planId}")
    public ResponseEntity<PlanResponseDto> updateContentPlanById(
            @PathVariable Long planId,
            @RequestBody PlanUpdateRequestDto dto,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();

        LoginResponseDto loginUser = (LoginResponseDto)session.getAttribute(LoginConst.LOGIN_USER);
        Long userId = loginUser.getId();

        return new ResponseEntity<>(planService.updatePlanContent(planId, dto.getContents(), userId), HttpStatus.OK);
    }

    // 일정삭제
    @DeleteMapping("/{planId}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long planId, HttpServletRequest request) {
        HttpSession session = request.getSession();

        LoginResponseDto loginUser = (LoginResponseDto)session.getAttribute(LoginConst.LOGIN_USER);
        Long userId = loginUser.getId();

        planService.deletePlan(planId, userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
