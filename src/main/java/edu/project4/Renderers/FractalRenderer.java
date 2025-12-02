package edu.project4.Renderers;

import edu.project4.Transformations.Transformation;
import edu.project4.shared.FractalImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public interface FractalRenderer {

    int COEFFS_COUNT = 7;

    @SuppressWarnings("MagicNumber")
    default List<double[]> generateCoeffs(long seed) {
        Random random = new Random(seed);
        List<double[]> coeffs = new ArrayList<>();

        for (int j = 0; j < COEFFS_COUNT; j++) {

            double[] cur = new double[9];
            while (true) {
                for (int i = 0; i < 6; i++) {
                    cur[i] = (random.nextDouble() - 0.5) * 2.5;
                }
                if (cur[0] * cur[0] + cur[3] * cur[3] >= 1) {
                    continue;
                }
                if (cur[1] * cur[1] + cur[4] * cur[4] >= 1) {
                    continue;
                }
                if (cur[0] * cur[0] + cur[1] * cur[1] + cur[3] * cur[3] + cur[4] * cur[4]
                    >= 1 + (cur[0] * cur[4] - cur[1] * cur[3])) {
                    continue;
                }
                break;
            }

            for (int i = 6; i < 9; i++) {
                cur[i] = random.nextInt(256);
            }

            coeffs.add(cur);
        }

        return coeffs;
    }

    FractalImage render(
        FractalImage canvas,
        List<Transformation> variations,
        int samples,
        int iterPerSample,
        int symmetry,
        long seed
    );
}
