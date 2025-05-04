package com.moji.tasknotification.service;

import com.moji.tasknotification.common.model.Task;
import com.moji.tasknotification.model.TaskEntity;
import com.moji.tasknotification.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::mapToModel)
                .collect(Collectors.toList());
    }

    public Optional<Task> getTaskById(String id) {
        return taskRepository.findById(id)
                .map(this::mapToModel);
    }

    public Task createTask(Task task) {
        TaskEntity entity = mapToEntity(task);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setCompleted(false);

        return mapToModel(taskRepository.save(entity));
    }

    public Optional<Task> updateTaskStatus(String id, boolean completed) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setCompleted(completed);
                    task.setUpdatedAt(LocalDateTime.now());
                    return mapToModel(taskRepository.save(task));
                });
    }

    public List<TaskEntity> findTasksWithApproachingDeadline() {
        // Find tasks that are due within the next 24 hours and not completed
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
        return taskRepository.findByCompletedFalseAndDeadlineBefore(tomorrow);
    }

    private Task mapToModel(TaskEntity entity) {
        return Task.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .deadline(entity.getDeadline())
                .completed(entity.isCompleted())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    private TaskEntity mapToEntity(Task model) {
        return TaskEntity.builder()
                .id(model.getId())
                .title(model.getTitle())
                .description(model.getDescription())
                .deadline(model.getDeadline())
                .completed(model.isCompleted())
                .createdAt(model.getCreatedAt())
                .updatedAt(model.getUpdatedAt())
                .build();
    }
}