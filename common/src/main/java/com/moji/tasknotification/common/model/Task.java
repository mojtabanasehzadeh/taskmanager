package com.moji.tasknotification.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private String id;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private boolean completed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}