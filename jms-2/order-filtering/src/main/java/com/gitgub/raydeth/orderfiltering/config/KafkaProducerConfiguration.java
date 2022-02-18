package com.gitgub.raydeth.orderfiltering.config;

import com.gitgub.raydeth.orderfiltering.model.ItemOrder;
import com.gitgub.raydeth.orderfiltering.model.LiquidOrder;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfiguration {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServer;

    @Bean
    public KafkaTemplate<String, ItemOrder> itemOrderKafkaTemplate() {
        return new KafkaTemplate<>(itemOrderProducerFactory());
    }

    @Bean
    public KafkaTemplate<String, LiquidOrder> liquidOrderKafkaTemplate() {
        return new KafkaTemplate<>(liquidOrderProducerFactory());
    }

    @Bean
    public ProducerFactory<String, ItemOrder> itemOrderProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public ProducerFactory<String, LiquidOrder> liquidOrderProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }
}
