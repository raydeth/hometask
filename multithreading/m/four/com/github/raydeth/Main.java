package com.github.raydeth;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Main {
//    public static void main(String[] args) {
//        BlockingObjectPool pool = new BlockingObjectPool(10);
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
//        scheduledExecutorService.scheduleAtFixedRate(produce(pool), 0, 100, TimeUnit.MILLISECONDS);
//        scheduledExecutorService.scheduleAtFixedRate(consume(pool), 100, 210, TimeUnit.MILLISECONDS);
//        scheduledExecutorService.scheduleAtFixedRate(consume(pool), 100, 210, TimeUnit.MILLISECONDS);
//    }

    public static void main(String[] args) {
        BlockingObjectPool pool = new BlockingObjectPool(10);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        scheduledExecutorService.scheduleAtFixedRate(produce(pool), 100, 100, TimeUnit.MILLISECONDS);
        scheduledExecutorService.scheduleAtFixedRate(consume(pool), 0, 210, TimeUnit.MILLISECONDS);
    }

    private static Runnable consume(BlockingObjectPool pool) {
        return () -> {
            try {
                Object value = pool.get();
                System.out.println(Thread.currentThread().getId() + ": " + value);
            } catch (RuntimeException e) {
                System.out.println(e);
                throw e;
            }
        };
    }

    private static Runnable produce(BlockingObjectPool pool) {
        return () -> {
            TestObject testObject = new TestObject(ThreadLocalRandom.current().nextLong(1, 99999999));
            try {
                pool.take(testObject);
            } catch (RuntimeException e) {
                System.out.println(e);
                throw e;
            }
        };
    }
}

class TestObject {

    private Long id;

    public TestObject(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TestObject: " + id;
    }
}
