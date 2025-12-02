package edu.project4.Renderers;

import edu.project4.Transformations.Transformation;
import edu.project4.shared.FractalImage;
import edu.project4.shared.Pixel;
import edu.project4.shared.Point;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

public class MultiThreadFractalRenderer implements FractalRenderer {

    private int nThreads;

    public MultiThreadFractalRenderer(int nThreads) {
        this.nThreads = nThreads;
    }

    @SuppressWarnings("MagicNumber")
    private void singleThreadActivity(
        FractalImage canvas,
        List<double[]> coeffs,
        List<Transformation> variations,
        int iterPerSample,
        int symmetry
    ) {
        int width = canvas.width();
        int height = canvas.height();

        Point point = new Point(
            (ThreadLocalRandom.current().nextDouble() - 0.5) * 2,
            (ThreadLocalRandom.current().nextDouble() - 0.5) * 2
        );

        for (int step = -20; step < iterPerSample; ++step) {
            double[] cur = coeffs.get(ThreadLocalRandom.current().nextInt(COEFFS_COUNT));
            point = new Point(
                point.x() * cur[0] + point.y() * cur[1] + cur[2],
                point.x() * cur[3] + point.y() * cur[4] + cur[5]
            );

            Transformation variation = variations.get(ThreadLocalRandom.current().nextInt(variations.size()));
            point = variation.apply(point, cur[2], cur[5]);

            double theta2 = 0;
            for (int s = 0; s < symmetry; theta2 += Math.PI * 2 / symmetry, ++s) {
                // rotate
                Point newPoint = new Point(
                    point.x() * Math.cos(theta2) - point.y() * Math.sin(theta2),
                    point.x() * Math.sin(theta2) + point.y() * Math.cos(theta2)
                );

                if (step < 0 || newPoint.x() > 1 || newPoint.y() > 1 || newPoint.x() < -1 || newPoint.y() < -1) {
                    continue;
                }

                Pixel pixel =
                    canvas.pixel((int) ((newPoint.x() + 1) / 2 * width), (int) ((newPoint.y() + 1) / 2 * height));
                synchronized (pixel) {
                    pixel.update((int) cur[6], (int) cur[7], (int) cur[8]);
                }
            }
        }
    }

    public FractalImage render(
        FractalImage canvas,
        List<Transformation> variations,
        int samples,
        int iterPerSample,
        int symmetry,
        long seed
    ) {
        List<double[]> coeffs = generateCoeffs(seed);
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);

        Future[] futures = new Future[samples];
        for (int sample = 0; sample < samples; ++sample) {
            futures[sample] = executorService.submit(() -> {
                singleThreadActivity(canvas, coeffs, variations, iterPerSample, symmetry);
            });
        }

        for (int sample = 0; sample < samples; ++sample) {
            try {
                futures[sample].get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        executorService.shutdown();
        return canvas;
    }

}
