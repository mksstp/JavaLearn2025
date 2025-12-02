package edu.hw8.Task2;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class FixedThreadPoolTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    @DisplayName("basic test")
    public void getFib_shouldCalculateFibForDifferentNumbersInMultipleThreads() {
        ThreadPool threadPool = FixedThreadPool.create(4);
        List<Integer> expected = List.of(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55);
        final List<Integer> actual = new CopyOnWriteArrayList<>();
        for (int i = 0; i <= 10; i++) {
            final int cur = i;
            threadPool.execute(() -> actual.add(Fibonacci.getFib(cur)));
        }
        try {
            Thread.sleep(2000);
            threadPool.close();
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        }
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }
}
