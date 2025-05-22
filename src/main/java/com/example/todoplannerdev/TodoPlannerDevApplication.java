package com.example.todoplannerdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TodoPlannerDevApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoPlannerDevApplication.class, args);
    }

}
