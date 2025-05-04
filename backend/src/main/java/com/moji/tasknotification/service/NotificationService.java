package com.moji.tasknotification.service;

import com.moji.tasknotification.common.model.Notification;
import com.moji.tasknotification.model.NotificationEntity;
import com.moji.tasknotification.model.TaskEntity;
import com.moji.tasknotification.repository.NotificationRepository;
import com.moji.tasknotification.websocket.NotificationMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll().stream()
                .map(this::mapToModel)
                .collect(Collectors.toList());
    }

    public List<Notification> getUnreadNotifications() {
        return notificationRepository.findByReadFalse().stream()
                .map(this::mapToModel)
                .collect(Collectors.toList());
    }

    public Notification createNotification(TaskEntity task) {
        NotificationEntity entity = NotificationEntity.builder()
                .title("Task Deadline Approaching")
                .message("Your task '" + task.getTitle() + "' is due soon!")
                .taskId(task.getId())
                .read(false)
                .createdAt(LocalDateTime.now())
                .build();

        NotificationEntity savedEntity = notificationRepository.save(entity);

        // Send notification via WebSocket
        sendNotification(savedEntity);

        return mapToModel(savedEntity);
    }

    public Optional<Notification> markAsRead(String id) {
        return notificationRepository.findById(id)
                .map(notification -> {
                    notification.setRead(true);
                    return mapToModel(notificationRepository.save(notification));
                });
    }

    private void sendNotification(NotificationEntity notification) {
        NotificationMessage message = NotificationMessage.builder()
                .id(notification.getId())
                .title(notification.getTitle())
                .message(notification.getMessage())
                .taskId(notification.getTaskId())
                .timestamp(notification.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build();

        messagingTemplate.convertAndSend("/topic/notifications", message);
    }

    private Notification mapToModel(NotificationEntity entity) {
        return Notification.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .message(entity.getMessage())
                .taskId(entity.getTaskId())
                .read(entity.isRead())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}