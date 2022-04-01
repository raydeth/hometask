package com.github.raydeth.service;

import com.github.raydeth.model.Order;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Slf4j
public class OrderService {

    private String fileName = "orders.txt";
    private static String path = "/Users/Maxim_Sherstoboyev/Education/Senior/hometask/aws/sqs/sqs-third/src/main/resources/";

    @SneakyThrows
    public void processOrder(Order order, String message) {
        File file = new File(path + fileName);
        FileUtils.write(file, message + order.toString() + "\n", StandardCharsets.UTF_8, true);
    }
}
