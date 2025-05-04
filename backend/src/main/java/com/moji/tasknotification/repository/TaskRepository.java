package com.moji.tasknotification.repository;

import com.moji.tasknotification.model.TaskEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<TaskEntity, String> {
    List<TaskEntity> findByCompletedFalseAndDeadlineBefore(LocalDateTime dateTime);
}