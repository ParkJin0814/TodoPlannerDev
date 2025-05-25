package com.example.todoplannerdev.dto;

import com.example.todoplannerdev.entity.Plan;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PlanResponseDto {
    private String name;
    private String title;
    private String contents;
    private int commentCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PlanResponseDto(Plan plan) {
        this.name = plan.getUser().getName();
        this.title = plan.getTitle();
        this.contents = plan.getContents();
        this.commentCount = plan.getComments().size();
        this.createdAt = plan.getCreatedAt();
        this.updatedAt = plan.getUpdatedAt();
    }
}
