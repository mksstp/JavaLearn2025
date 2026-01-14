package edu.basics8.Task2;

public interface ThreadPool extends AutoCloseable {
    void start();

    void execute(Runnable runnable);
}
