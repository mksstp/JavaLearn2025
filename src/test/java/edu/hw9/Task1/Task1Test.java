package edu.hw9.Task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Статистики")
    void statistics() {
        // given
        StatsCollector collector = new StatsCollector();
        List<double[]> metrics = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            metrics.add(new double[] {0.1D * i, 0.2D * i, 0.4D * i, 0.3D * i});
        }
        CountDownLatch latch = new CountDownLatch(1);
        Map<String, Double> answer;

        // when
        try (ExecutorService service = Executors.newFixedThreadPool(4)) {
            for (int i = 0; i < 100; i++) {
                int finalI = i;
                service.execute(() -> collector.push("Metric n" + finalI, metrics.get(finalI)));
            }
            service.shutdown();
            try {
                if (!service.awaitTermination(60, TimeUnit.SECONDS)) {
                    service.shutdownNow();
                    if (!service.awaitTermination(60, TimeUnit.SECONDS))
                        System.err.println("Pool did not terminate");
                }
            } catch (InterruptedException ie) {
                service.shutdownNow();
                Thread.currentThread().interrupt();
            }
            latch.countDown();
            latch.await();
            answer = collector.stats();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // then
        assertThat(answer.size()).isEqualTo(400);
        assertThat(answer.get("Sum of Metric n1")).isEqualTo(1D);
        assertThat(answer.get("Max of Metric n99")).isEqualTo(39.6D);
        assertThat(answer.get("Min of Metric n10")).isEqualTo(1D);
        assertThat(answer.get("Average of Metric n18")).isEqualTo(4.5D);
    }
}
