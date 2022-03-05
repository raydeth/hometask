package com.github.raydeth.kafka.clientapp;

import com.github.raydeth.kafka.clientapp.config.KafkaConfiguration;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientAppApplication.class, properties = "spring.main.allow-bean-definition-overriding=true")
@ContextConfiguration(classes = KafkaConfiguration.class)
public class ClientAppApplicationTests {


    @ClassRule
    public static final KafkaContainer KAFKA_CONTAINER = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.1"));

}
