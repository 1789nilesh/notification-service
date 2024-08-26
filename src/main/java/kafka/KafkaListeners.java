package kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import service.NotificationService;

@Component
public class KafkaListeners {

    private final NotificationService notificationService;

    public KafkaListeners(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "blog-post-events", groupId = "notification-group")
    public void listenBlogPostEvents(String message) {
        notificationService.sendNotification(message);
    }

    @KafkaListener(topics = "comment-events", groupId = "notification-group")
    public void listenCommentEvents(String message) {
        notificationService.sendNotification(message);
    }
}