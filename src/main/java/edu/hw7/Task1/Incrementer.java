package edu.hw7.Task1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class Incrementer {

    private final AtomicInteger atomicInteger;

    Incrementer(int value) {
        atomicInteger = new AtomicInteger(value);
    }

    void set(int value) {
        this.atomicInteger.set(value);
    }

    int get() {
        return this.atomicInteger.get();
    }

    void multiThreadIncrement(int threadCount, int incrementPerThread) {
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < incrementPerThread; j++) {
                    atomicInteger.incrementAndGet();
                }
            });

            threads[i].start();
        }

        try {
            for (int i = 0; i < threadCount; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            Logger.getLogger("logger").info("Error while multithreading");
        }
    }
}
