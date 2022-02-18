package com.github.raydethorder.handler.service.impl;

import com.github.raydethorder.handler.service.LogService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
public class LogServiceImpl implements LogService {

    @Value("${file.path}")
    private String filePath;

    @Value("${file.name}")
    private String fileName;

    private BufferedWriter logFile;

    @Override
    public void logMessage(String message) {
        try {
            logFile.write(message);
            logFile.newLine();
            logFile.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void preDestroy() {
        try {
            logFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void postConstruct() {
        try {
            logFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath + "/" + fileName), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
