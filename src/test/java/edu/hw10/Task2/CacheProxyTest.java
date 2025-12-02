package edu.hw10.Task2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CacheProxyTest {
    private static final Logger LOGGER = LogManager.getLogger();
    @Test
    @DisplayName("Проверка создания кэша")
    public void testCachingProxy() throws IOException {
        // Given
        FibCalculator fibCalculator = new FibCalculator() {
            @Override
            public long fib(int number) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    LOGGER.error(e.getStackTrace());
                }

                if (number <= 1) {
                    return number;
                } else {
                    return fib(number - 1) + fib(number - 2);
                }
            }
        };
        Path tempDirectory = Files.createTempDirectory("cache");
        String cacheDirectory = tempDirectory.toString();

        FibCalculator proxy = CacheProxy.create(fibCalculator, FibCalculator.class, cacheDirectory);

        // When
        long result1 = proxy.fib(2);
        long result2 = proxy.fib(2);
        long result3 = proxy.fib(3);

        // Then
        assertEquals(1, result1);
        assertTrue(Files.exists(Paths.get(cacheDirectory, "fib2")));
        assertEquals(result1, result2);
        assertNotEquals(result1, result3);
    }
}
