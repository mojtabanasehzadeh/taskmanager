package com.moji.tasknotification.config;

import com.moji.tasknotification.model.TaskEntity;
import com.moji.tasknotification.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final TaskRepository taskRepository;

    @Override
    public void run(String... args) {
        if (taskRepository.count() == 0) {
            log.info("Initializing sample data...");

            // Create a task due in 30 minutes
            TaskEntity task1 = TaskEntity.builder()
                    .title("Complete Project Setup")
                    .description("Finish setting up the multi-module project structure")
                    .deadline(LocalDateTime.now().plusMinutes(30))
                    .completed(false)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            // Create a task due tomorrow
            TaskEntity task2 = TaskEntity.builder()
                    .title("Implement Frontend")
                    .description("Create the React Native frontend for the application")
                    .deadline(LocalDateTime.now().plusDays(1))
                    .completed(false)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            taskRepository.save(task1);
            taskRepository.save(task2);

            log.info("Sample data initialized.");
        }
    }
}