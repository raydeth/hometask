package com.github.raydeth.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("rabbit")
public class RabbitConfiguration {

    @Value("${jms.event.topic.notification.name}")
    private String notificationExchangeName;

    @Value("${jms.event.topic.request.name}")
    private String requestExchangeName;

    @Value("${jms.event.topic.request.create}")
    private String createRequestQueueName;

    @Value("${jms.event.topic.request.update}")
    private String updateRequestQueueName;

    @Value("${jms.event.topic.request.delete}")
    private String deleteRequestQueueName;

    @Value("${jms.event.topic.notification.create}")
    private String createNotificationEventQueueName;

    @Value("${jms.event.topic.notification.update}")
    private String updateNotificationEventQueueName;

    @Value("${jms.event.topic.notification.delete}")
    private String deleteNotificationEventQueueName;

    @Value("${rabbit.address}")
    private String address;

    @Value("${rabbit.user}")
    private String user;

    @Value("${rabbit.password}")
    private String password;

    @Bean
    public CachingConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public RabbitAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public DirectExchange notificationExchange() {
        return new DirectExchange(notificationExchangeName);
    }

    @Bean
    public DirectExchange requestExchange() {
        return new DirectExchange(requestExchangeName);
    }

    @Bean
    public Queue createEventRequestQueue() {
        return new Queue(createRequestQueueName
    }

    @Bean
    public Queue updateEventRequestQueue() {
        return new Queue(updateRequestQueueName
    }

    @Bean
    public Queue deleteEventRequestQueue() {
        return new Queue(deleteRequestQueueName
    }

    @Bean
    public Queue createNotificationEventQueue() {
        return new Queue(createNotificationEventQueueName
    }

    @Bean
    public Queue updateNotificationEventQueue() {
        return new Queue(updateNotificationEventQueueName
    }

    @Bean
    public Queue deleteNotificationEventQueue() {
        return new Queue(deleteNotificationEventQueueName
    }

    @Bean
    public Binding bindingCreateNotificationEvent() {
        return BindingBuilder.bind(createNotificationEventQueue()).to(notificationExchange()).with(createNotificationEventQueueName);
    }

    @Bean
    public Binding bindingUpdateNotificationEvent() {
        return BindingBuilder.bind(updateNotificationEventQueue()).to(notificationExchange()).with(updateNotificationEventQueueName);
    }

    @Bean
    public Binding bindingDeleteNotificationEvent() {
        return BindingBuilder.bind(deleteNotificationEventQueue()).to(notificationExchange()).with(deleteNotificationEventQueueName);
    }

    @Bean
    public Binding createDeleteNotificationEvent() {
        return BindingBuilder.bind(createEventRequestQueue()).to(notificationExchange()).with(createRequestQueueName);
    }

    @Bean
    public Binding updateDeleteNotificationEvent() {
        return BindingBuilder.bind(updateEventRequestQueue()).to(notificationExchange()).with(updateRequestQueueName);
    }

    @Bean
    public Binding deleteDeleteNotificationEvent() {
        return BindingBuilder.bind(deleteEventRequestQueue()).to(notificationExchange()).with(deleteRequestQueueName);
    }

}
