package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Task {

    private Long id;  // ID больше не генерируется автоматически через базу данных

    private String code; // Например, "task1", "task2"

    private String title;

    private String description;

    private String link;

    private int rewardStars;

    private boolean active = true;

    private LocalDateTime createdAt = LocalDateTime.now();

    protected Task(String code, String description, String link, int rewardStars, boolean active) {
        this.code = code;
        this.description = description;
        this.link = link;
        this.rewardStars = rewardStars;
        this.active = active;
    }

    public abstract String getTaskCode();


}