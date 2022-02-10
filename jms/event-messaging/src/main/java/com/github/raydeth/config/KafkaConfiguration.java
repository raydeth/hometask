package com.github.raydeth.config;

import com.github.raydeth.model.Event;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Profile("kafka")
public class KafkaConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String consumerGroupId;

    @Bean
    public Map<String, Object> consumerProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return props;
    }

    @Bean
    public ConsumerFactory<String, Event> defaultEventRequestConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerProperties(), new StringDeserializer(), new JsonDeserializer<>(Event.class));
    }

    @Bean
    public ConsumerFactory<String, Long> deleteEventRequestConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerProperties(), new StringDeserializer(), new JsonDeserializer<>(Long.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Event> defaultEventRequestContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Event> containerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        containerFactory.setConsumerFactory(defaultEventRequestConsumerFactory());
        return containerFactory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Long> deleteEventRequestContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Long> containerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        containerFactory.setConsumerFactory(deleteEventRequestConsumerFactory());
        return containerFactory;
    }
}
