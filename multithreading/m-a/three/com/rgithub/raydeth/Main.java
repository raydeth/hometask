package com.rgithub.raydeth;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    private volatile AtomicInteger progressPercentage = new AtomicInteger();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Main main = new Main();
        main.run();
    }

    private void run() throws ExecutionException, InterruptedException {
        Scanner in = new Scanner(System.in);
        System.out.print("Input folder path: ");
        String basePath = in.next();
//        String basePath = "/Users/Maxim_Sherstoboyev";

        File baseDirectory = new File(basePath);
        if (!baseDirectory.exists()) {
            throw new RuntimeException("A directory does not exists");
        }
        if (!baseDirectory.isDirectory()) {
            throw new RuntimeException("Inputted path is a file");
        }

        List<File> directories = new ArrayList<>();
        List<File> files = new ArrayList<>();
        for (File file : baseDirectory.listFiles()) {
            if (file.isDirectory()) {
                directories.add(file);
            } else {
                files.add(file);
            }
        }

        ForkJoinPool pool = new ForkJoinPool();

        AtomicInteger filesProgressPercentage = new AtomicInteger();
        ForkJoinTask<Long> filesTask = pool.submit(new CalculateFilesTotalSum(files, filesProgressPercentage));

        AtomicInteger directoriesProgressPercentage = new AtomicInteger();
        ForkJoinTask<Long> directoriesTask = pool.submit(new CalculateFilesTotalSum(directories, directoriesProgressPercentage));

        ForkJoinTask<?> progressBarTask = pool.submit(new ProgressBar(List.of(filesProgressPercentage, directoriesProgressPercentage)));

        pool.execute(new TerminateConsoleListener(List.of(filesTask, directoriesTask, progressBarTask)));

        try {
            Long filesSize = filesTask.get();
            Long directoriesSize = directoriesTask.get();
            progressBarTask.get();
            System.out.println("Directories count: " + directories.size());
            System.out.println("Files count: " + files.size());
            System.out.println("Total size: " + filesSize + directoriesSize);

        } catch (CancellationException e) {
            System.out.println("Accumulating information was rejected");
        }
        pool.shutdown();
    }
}

class CalculateFilesTotalSum implements Callable<Long> {

    private final List<File> files;
    private final AtomicInteger progressPercentage;

    public CalculateFilesTotalSum(List<File> files, AtomicInteger progressPercentage) {
        this.files = files;
        this.progressPercentage = progressPercentage;
    }

    @Override
    public Long call() {
        long totalSum = 0;
        for (int i = 0; i < files.size(); i++) {
            File file = files.get(i);
            try {
                Thread.sleep(100);
                totalSum += Files.size(file.toPath());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

            int percentage = ((i + 1) * 100) / files.size();
            progressPercentage.set(percentage);
        }

        return totalSum;
    }
}

class TerminateConsoleListener implements Runnable {

    private final List<ForkJoinTask> terminateTasks;

    public TerminateConsoleListener(List<ForkJoinTask> terminateTasks) {
        this.terminateTasks = terminateTasks;
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        // Reading data using readLine
//        int command = in.nextInt();
        int command = 0;
        try {
            command = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (command == 1) {
            terminateTasks.forEach(tt -> tt.cancel(true));
        }
    }
}

class ProgressBar implements Runnable {

    private final List<AtomicInteger> percentages;

    public ProgressBar(List<AtomicInteger> percentages) {
        this.percentages = percentages;
    }

    @Override
    public void run() {
        int percentage = 0;
        while (percentage < 100) {
            int sum = percentages.stream().mapToInt(AtomicInteger::get).sum();
            percentage = sum / percentages.size();

            System.out.printf("\rScanning [%d%%]", percentage);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }
}

// /Users/Maxim_Sherstoboyev