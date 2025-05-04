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
public class Notification {
    private String id;
    private String title;
    private String message;
    private String taskId;
    private boolean read;
    private LocalDateTime createdAt;
}