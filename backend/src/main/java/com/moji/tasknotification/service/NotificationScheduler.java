package com.moji.tasknotification.service;

import com.moji.tasknotification.model.TaskEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationScheduler {

    private final TaskService taskService;
    private final NotificationService notificationService;

    @Scheduled(fixedDelay = 60000) // Check every minute
    public void checkTaskDeadlines() {
        log.info("Checking for tasks with approaching deadlines...");

        List<TaskEntity> approachingDeadlineTasks = taskService.findTasksWithApproachingDeadline();

        for (TaskEntity task : approachingDeadlineTasks) {
            log.info("Creating notification for task: {}", task.getTitle());
            notificationService.createNotification(task);
        }
    }
}