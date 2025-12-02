package edu.hw8.Task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class FixedThreadPool implements ThreadPool {
    private final static Logger LOGGER = LogManager.getLogger();

    private final BlockingQueue<Runnable> tasks;
    private final Worker[] threads;
    private final AtomicBoolean isShutdown;

    private FixedThreadPool(Worker[] threads) {
        this.threads = threads;
        this.tasks = new LinkedBlockingQueue<>();
        this.isShutdown = new AtomicBoolean(false);
        start();
    }

    public static FixedThreadPool create(int numberOfThreads) {
        if (numberOfThreads <= 0) {
            throw new IllegalArgumentException("number of threads should be > 0");
        }
        return new FixedThreadPool(new Worker[numberOfThreads]);
    }

    @Override
    public void start() {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Worker();
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (!isShutdown.get()) {
            try {
                tasks.put(runnable);
            } catch (InterruptedException e) {
                LOGGER.info(e.getStackTrace());
            }
        }
    }

    @Override
    public void close() {
        isShutdown.set(true);
        for (Thread thread : threads) {
            if (!thread.isInterrupted()) {
                thread.interrupt();
            }
        }
    }

    private final class Worker extends Thread {

        @Override
        public void run() {
            while (!isShutdown.get() || !tasks.isEmpty()) {
                Runnable runnable;
                while ((runnable = tasks.poll()) != null) {
                    runnable.run();
                }
            }
        }
    }
}
