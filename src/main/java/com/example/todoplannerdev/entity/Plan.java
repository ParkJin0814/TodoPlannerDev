package com.example.todoplannerdev.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "plan")
@Getter
public class Plan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String title;

    @Column(length = 200, nullable = false)
    private String contents;


    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
