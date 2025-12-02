package edu.hw9.Task1;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class StatsCollector {
    private final ExecutorService threadPool;
    private final Map<String, Double> output;
    private static final int NUMBER_OF_THREADS = 4;
    private static final int SECONDS_TO_WAIT_FOR_TASKS_TO_FINISH = 60;

    public StatsCollector() {
        threadPool = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        output = new ConcurrentHashMap<>();
    }

    public void push(String metric, double[] numbers) {
        threadPool.execute(() -> {
            Double answer = Arrays.stream(numbers).sum();
            String name = "Sum of " + metric;
            output.put(name, answer);
        });

        threadPool.execute(() -> {
            Double answer = null;
            if (Arrays.stream(numbers).average().isPresent()) {
                answer = Arrays.stream(numbers).average().getAsDouble();
            }
            String name = "Average of " + metric;
            output.put(name, answer);
        });

        threadPool.execute(() -> {
            Double answer = null;
            if (Arrays.stream(numbers).max().isPresent()) {
                answer = Arrays.stream(numbers).max().getAsDouble();
            }
            String name = "Max of " + metric;
            output.put(name, answer);
        });

        threadPool.execute(() -> {
            Double answer = null;
            if (Arrays.stream(numbers).min().isPresent()) {
                answer = Arrays.stream(numbers).min().getAsDouble();
            }
            String name = "Min of " + metric;
            output.put(name, answer);
        });
    }

    public Map<String, Double> stats() {
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(SECONDS_TO_WAIT_FOR_TASKS_TO_FINISH, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
                if (!threadPool.awaitTermination(SECONDS_TO_WAIT_FOR_TASKS_TO_FINISH, TimeUnit.SECONDS)) {
                    System.err.println("Pool did not terminate");
                }
            }
        } catch (InterruptedException e) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
        return output;
    }
}
