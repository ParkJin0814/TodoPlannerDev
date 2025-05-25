package com.example.todoplannerdev.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "plan")
@Getter
@NoArgsConstructor
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

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    public Plan(String title, String contents, User user) {
        this.title = title;
        this.contents = contents;
        this.user = user;
    }

    public void updateContents(String contents) {
        this.contents = contents;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
