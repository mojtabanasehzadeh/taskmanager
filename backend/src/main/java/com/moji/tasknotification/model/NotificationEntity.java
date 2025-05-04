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
@Document(collection = "notifications")
public class NotificationEntity {
    @Id
    private String id;
    private String title;
    private String message;
    private String taskId;
    private boolean read;
    private LocalDateTime createdAt;
}