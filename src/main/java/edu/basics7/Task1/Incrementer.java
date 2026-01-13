package edu.basics7.Task1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class Incrementer {
    private final AtomicInteger atomicInteger;

    Incrementer(int value) {
        this.atomicInteger = new AtomicInteger(value);
    }

    int get() {
        return this.atomicInteger.get();
    }

    void set(int value) {
        this.atomicInteger.set(value);
    }

    void multiThreadIncrement(int threadQuantity, int incrementPerThread) {
        Thread[] threads = new Thread[threadQuantity];
        for (int i = 0; i < threadQuantity; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < incrementPerThread; j++) {
                    atomicInteger.incrementAndGet();
                }
            });
            threads[i].start();
        }

        try {
            for (int i = 0; i < threadQuantity; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            Logger.getLogger("logger").info("Error while multithreading");
        }
    }
}
