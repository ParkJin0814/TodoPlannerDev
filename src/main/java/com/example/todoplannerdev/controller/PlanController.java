package com.example.todoplannerdev.controller;

import com.example.todoplannerdev.dto.PlanRequestDto;
import com.example.todoplannerdev.dto.PlanResponseDto;
import com.example.todoplannerdev.dto.PlanUpdateRequestDto;
import com.example.todoplannerdev.service.PlanService;
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
    public ResponseEntity<PlanResponseDto> createPlan(@RequestBody PlanRequestDto dto) {
        return new ResponseEntity<>(planService.createPlan(dto), HttpStatus.CREATED);
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
            @RequestBody PlanUpdateRequestDto dto
    ) {
        return new ResponseEntity<>(planService.updatePlanContent(planId, dto.getContents()), HttpStatus.OK);
    }

    // 일정삭제
    @DeleteMapping("/{planId}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long planId) {
        planService.deletePlan(planId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
