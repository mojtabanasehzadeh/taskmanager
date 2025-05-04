package com.moji.tasknotification.repository;

import com.moji.tasknotification.model.NotificationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends MongoRepository<NotificationEntity, String> {
    List<NotificationEntity> findByTaskIdAndReadFalse(String taskId);

    List<NotificationEntity> findByReadFalse();
}