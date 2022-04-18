package com.github.raydeth;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    private static final List<Integer> numbers = new ArrayList<>();
    private static final CyclicBarrier barrier = new CyclicBarrier(3);

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
        executorService.scheduleAtFixedRate(generateRandomNumbers(), 0, 1, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(printTotalSum(), 0, 500, TimeUnit.MILLISECONDS);
        executorService.scheduleAtFixedRate(printSquare(), 0, 500, TimeUnit.MILLISECONDS);
    }

    private static Runnable printSquare() {
        return () -> {
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            synchronized (numbers) {
                System.out.println("Square: " + Math.sqrt(numbers.stream().map(n -> Math.pow(n, 2)).reduce(0D, Double::sum)));
            }
        };
    }

    private static Runnable generateRandomNumbers() {
        return () -> {
            synchronized (numbers) {
                int generatedValue = (int) (Math.random() * 10);
                System.out.println("Generated value: " + generatedValue);
                numbers.add(generatedValue);
            }
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            barrier.reset();
        };
    }

    private static Runnable printTotalSum() {
        return () -> {
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            synchronized (numbers) {
                System.out.println("Total sum: " + numbers.stream().reduce(0, Integer::sum));
            }
        };
    }
}
