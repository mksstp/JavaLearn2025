package edu.basics7.Task4;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class PiFinder {
    private final AtomicInteger pointsInCircleCount = new AtomicInteger();

    private int pointsInCircleCounter(int counter) {
        int res = 0;
        for (int i = 0; i < counter; i++) {
            double x = ThreadLocalRandom.current().nextDouble(2);
            double y = ThreadLocalRandom.current().nextDouble(2);
            double distance = (x - 1) * (x - 1) + (y - 1) * (y - 1);
            if (distance <= 1) {
                res++;
            }
        }
        return res;
    }

    @SuppressWarnings("MagicNumber")
    public Double multiThreadCalculatePiNumber(int threadCount, int pointsCount) {
        pointsInCircleCount.set(0);

        int pointsPerThread = pointsCount / threadCount;
        int pointsLastThread = pointsCount - (threadCount - 1) * pointsPerThread;

        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            int currentPointsCount = (i == threadCount - 1) ? pointsLastThread : pointsPerThread;
            threads[i] = new Thread(() -> pointsInCircleCount.addAndGet(pointsInCircleCounter(currentPointsCount)));
            threads[i].start();
        }

        try {
            for (int i = 0; i < threadCount; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            return null;
        }

        return 4 * pointsInCircleCount.get() / (double) pointsCount;

    }

    @SuppressWarnings("MagicNumber")
    public double calculatePiNumber(int pointsCount) {
        int pointsInCircle = pointsInCircleCounter(pointsCount);
        return 4 * pointsInCircle / (double) pointsCount;
    }
}
