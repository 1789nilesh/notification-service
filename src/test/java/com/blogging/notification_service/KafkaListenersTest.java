package com.blogging.notification_service;

import kafka.KafkaListeners;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import service.NotificationService;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = { "blog-post-events", "comment-events" })
public class KafkaListenersTest {

    @Test
    void testListenBlogPostEvents() {
        NotificationService notificationService = Mockito.mock(NotificationService.class);
        KafkaListeners kafkaListeners = new KafkaListeners(notificationService);

        kafkaListeners.listenBlogPostEvents("New Blog Post Created");
        Mockito.verify(notificationService, Mockito.times(1)).sendNotification("New Blog Post Created");
    }

    @Test
    void testListenCommentEvents() {
        NotificationService notificationService = Mockito.mock(NotificationService.class);
        KafkaListeners kafkaListeners = new KafkaListeners(notificationService);

        kafkaListeners.listenCommentEvents("New Comment Added");
        Mockito.verify(notificationService, Mockito.times(1)).sendNotification("New Comment Added");
    }
}