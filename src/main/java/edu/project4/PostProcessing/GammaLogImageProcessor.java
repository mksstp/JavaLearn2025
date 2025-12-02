package edu.project4.PostProcessing;

import edu.project4.shared.FractalImage;
import edu.project4.shared.Pixel;

public class GammaLogImageProcessor implements ImageProcessor {

    final double gamma = 2.2;

    public void process(FractalImage image) {

        double max = 0.0;
        for (int row = 0; row < image.height(); row++) {
            for (int col = 0; col < image.width(); col++) {
                Pixel pixel = image.data()[row][col];
                if (pixel.hitCount() != 0) {
                    pixel.setNormal(Math.log10(pixel.hitCount()));
                    if (pixel.normal() > max) {
                        max = pixel.normal();
                    }
                }
            }
        }
        for (int row = 0; row < image.height(); row++) {
            for (int col = 0; col < image.width(); col++) {
                Pixel pixel = image.data()[row][col];
                pixel.setNormal(pixel.normal() / max);

                int r = (int) ((double) pixel.r() * Math.pow(pixel.normal(), (1.0 / gamma)));
                int g = (int) ((double) pixel.g() * Math.pow(pixel.normal(), (1.0 / gamma)));
                int b = (int) ((double) pixel.b() * Math.pow(pixel.normal(), (1.0 / gamma)));
                pixel.setRGB(r, g, b);
            }
        }

    }
}
