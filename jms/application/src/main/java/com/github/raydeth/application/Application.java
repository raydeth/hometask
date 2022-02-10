package com.github.raydeth.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.github.raydeth"})
@EnableJpaRepositories(basePackages="com.github.raydeth.repository")
@EntityScan("com.github.raydeth.model")
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
