package com.moji.tasknotification.graphql;

import com.moji.tasknotification.common.model.Task;
import com.moji.tasknotification.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @QueryMapping
    public List<Task> tasks() {
        return taskService.getAllTasks();
    }

    @QueryMapping
    public Task task(@Argument String id) {
        return taskService.getTaskById(id).orElse(null);
    }

    @MutationMapping
    public Task createTask(@Argument TaskInput input) {
        Task task = Task.builder()
                .title(input.getTitle())
                .description(input.getDescription())
                .deadline(input.getDeadline())
                .build();
        return taskService.createTask(task);
    }

    @MutationMapping
    public Task updateTaskStatus(@Argument String id, @Argument boolean completed) {
        return taskService.updateTaskStatus(id, completed).orElse(null);
    }

    public static class TaskInput {
        private String title;
        private String description;
        private LocalDateTime deadline;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public LocalDateTime getDeadline() {
            return deadline;
        }

        public void setDeadline(LocalDateTime deadline) {
            this.deadline = deadline;
        }
    }
}