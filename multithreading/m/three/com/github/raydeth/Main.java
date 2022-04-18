package com.github.raydeth;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Main {

    final List<Topic<String>> topics = new ArrayList<>();
    volatile List<ConsumerOffset<String>> consumerOffsetList = new ArrayList<>();
    WordGenerator wordGenerator = new WordGenerator();

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run() {
        createTopics();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        scheduledExecutorService.scheduleAtFixedRate(this.producer(), 0, 1, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(this.consumer("Messages", 1L), 0, 500, TimeUnit.MILLISECONDS);
        scheduledExecutorService.scheduleAtFixedRate(this.consumer("Messages", 2L), 0, 500, TimeUnit.MILLISECONDS);
        scheduledExecutorService.scheduleAtFixedRate(this.consumer("Users", 1L), 0, 500, TimeUnit.MILLISECONDS);
    }

    private void createTopics() {
        synchronized (topics) {
            topics.add(createTopic("Messages"));
            topics.add(createTopic("Users"));
        }
    }

    private Topic<String> createTopic(String topicName) {
        return new Topic<>(topicName);
    }

    private Runnable producer() {
        return () -> {
            String word = wordGenerator.generate();
            Topic<String> randomTopic;
            synchronized (topics) {
                randomTopic = topics.get(ThreadLocalRandom.current().nextInt(0, topics.size()));
            }
            Queue<String> queue = randomTopic.getQueue();
            synchronized (queue) {
                queue.offer(word);
            }
        };
    }

    private Runnable consumer(String topicName, Long consumerId) {
        return () -> {
            Topic<String> topic;
            synchronized (topics) {
                topic = topics.stream().filter(t -> t.getName().equals(topicName)).findFirst().orElseThrow(NullPointerException::new);
            }
            ConsumerOffset<String> consumerOffset;
            synchronized (consumerOffsetList) {
                consumerOffset = consumerOffsetList.stream().filter(co -> co.getTopicName().equals(topicName)
                        && co.getId().equals(consumerId)).findFirst().orElseGet(() -> {
                    ConsumerOffset<String> newConsumerOffset = new ConsumerOffset<>();
                    newConsumerOffset.setId(consumerId);
                    newConsumerOffset.setTopicName(topicName);
                    newConsumerOffset.setOffset(-1L);

                    consumerOffsetList.add(newConsumerOffset);

                    return newConsumerOffset;
                });
            }
            Queue<String> queue = topic.getQueue();
            synchronized (queue) {
                if (consumerOffset.getOffset() < queue.size()) {
                    Iterator<String> iterator = queue.iterator();
                    for (int i = -1; i < consumerOffset.getOffset(); i++) {
                        iterator.next();
                    }
                    for (; consumerOffset.getOffset() < queue.size() - 1; ) {
                        String text = iterator.next();
                        System.out.printf("Topic: %s\t Consumer id: %d Message: %s\n", topic.getName(), consumerId, text);

                        consumerOffset.setOffset(consumerOffset.getOffset() + 1);
                    }
                }
            }
        };
    }

}
