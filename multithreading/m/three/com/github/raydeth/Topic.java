package com.github.raydeth;

import java.util.Iterator;
import java.util.Queue;

public class Topic<T> {
    private String name;
    private Queue<T> queue = new LinkedQueue<>();

    public Topic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Queue<T> getQueue() {
        return queue;
    }

    public void setQueue(Queue<T> queue) {
        this.queue = queue;
    }

}
