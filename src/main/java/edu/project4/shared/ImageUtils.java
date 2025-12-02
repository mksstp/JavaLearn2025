package edu.project4.shared;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class ImageUtils {

    private static final int RSHIFT = 16;
    private static final int GSHIFT = 8;

    private ImageUtils() {
    }

    public static void save(FractalImage image, String filename, ImageFormat format) {
        BufferedImage imageOut = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_3BYTE_BGR);

        for (int i = 0; i < image.width(); i++) {
            for (int j = 0; j < image.height(); j++) {
                int r = image.pixel(i, j).r();
                int g = image.pixel(i, j).g();
                int b = image.pixel(i, j).b();
                imageOut.setRGB(i, j, (r << RSHIFT) + (g << GSHIFT) + b);
            }
        }

        File outputfile = new File("src/main/resources/" + filename);

        try {
            ImageIO.write(imageOut, format.toString(), outputfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
