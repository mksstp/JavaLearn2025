package edu.hw7.Task4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PiFinderTest {

    private final static Logger LOGGER = LogManager.getLogger();

    @Test
    @DisplayName("One thread")
    void piTest1() {
        PiFinder piFinder = new PiFinder();

        for (int i = 0; i < 10; i++) {
            assertTrue(Math.abs(Math.PI - piFinder.calculatePiNumber(100000)) <= 0.05);
        }
    }

    @Test
    @DisplayName("Multithread")
    void piTest2() {
        PiFinder piFinder = new PiFinder();

        for (int i = 0; i < 10; i++) {
            assertTrue(Math.abs(Math.PI - piFinder.multithreadCalculatePiNumber(5, 100000)) <= 0.05);
        }
    }

    @Test
    @DisplayName("piFinder statistics")
    @Disabled
    void piTestStatistics() {
        PiFinder piFinder = new PiFinder();
        List<Integer> pointsCount = Arrays.asList(100, 10000, 1000000, 10000000);
        List<Integer> threadsCount = Arrays.asList(1, 2, 4, 8, 16);
        int triesCount = 10;

        Path filePath = Paths.get("src/main/resources/hw7/piStatistic.txt");
        try {
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }
            Files.createFile(filePath);

            try (FileOutputStream fos = new FileOutputStream("src/main/resources/hw7/piStatistic.txt");
                 PrintStream printStream = new PrintStream(fos)) {

                for (var points : pointsCount) {
                    for (var threadCount : threadsCount) {

                        double totalAccuracy = 0;
                        long totalTime = 0;

                        for (int i = 0; i < triesCount; i++) {
                            Long start = System.nanoTime();

                            double result;
                            if (threadCount == 1) {
                                result = piFinder.calculatePiNumber(points);
                            } else {
                                result = piFinder.multithreadCalculatePiNumber(threadCount, points);
                            }

                            Long end = System.nanoTime();

                            totalAccuracy += Math.abs(Math.PI - result);
                            totalTime += end - start;
                        }

                        printStream.printf(
                            "Points: %d, Threads: %d, Accuracy: %f, MilliSeconds: %d\n",
                            points,
                            threadCount,
                            totalAccuracy / triesCount,
                            totalTime / triesCount / 1000000
                        );
                    }
                    printStream.println();
                }

            } catch (IOException e) {
                LOGGER.info(e.getMessage());
            }
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }

    }

}
