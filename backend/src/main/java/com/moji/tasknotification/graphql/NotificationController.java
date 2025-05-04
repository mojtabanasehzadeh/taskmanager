package com.moji.tasknotification.graphql;

import com.moji.tasknotification.common.model.Notification;
import com.moji.tasknotification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @QueryMapping
    public List<Notification> notifications() {
        return notificationService.getAllNotifications();
    }

    @QueryMapping
    public List<Notification> unreadNotifications() {
        return notificationService.getUnreadNotifications();
    }

    @MutationMapping
    public Notification markNotificationAsRead(@Argument NotificationMarkReadInput input) {
        return notificationService.markAsRead(input.getId()).orElse(null);
    }

    public static class NotificationMarkReadInput {
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}