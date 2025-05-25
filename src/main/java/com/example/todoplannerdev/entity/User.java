package com.example.todoplannerdev.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 4, nullable = false)
    private String name;
    @Column(length = 40, nullable = false, unique = true)
    private String email;
    @Column(length = 40, nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Plan> plans;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;


    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void addPlan(Plan plan) {
        plans.add(plan);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
