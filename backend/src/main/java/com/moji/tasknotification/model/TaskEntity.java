package com.moji.tasknotification.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tasks")
public class TaskEntity {
    @Id
    private String id;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private boolean completed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}