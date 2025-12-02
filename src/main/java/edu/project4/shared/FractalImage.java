package edu.project4.shared;

public record FractalImage(Pixel[][] data, int width, int height) {
    public static FractalImage create(int width, int height) {
        FractalImage fractalImage = new FractalImage(new Pixel[height][width], width, height);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                fractalImage.data[i][j] = new Pixel();
            }
        }
        return fractalImage;
    }

    public Pixel pixel(int x, int y) {
        return data[y][x];
    }
}
