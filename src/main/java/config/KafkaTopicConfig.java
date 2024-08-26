package config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic blogPostTopic() {
        return new NewTopic("blog-post-events", 1, (short) 1);
    }

    @Bean
    public NewTopic commentTopic() {
        return new NewTopic("comment-events", 1, (short) 1);
    }
}