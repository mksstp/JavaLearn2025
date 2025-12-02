package edu.project4.shared;

public class Pixel {

    private int r;
    private int g;
    private int b;
    private double normal;
    private int hitCount;

    public Pixel() {
        hitCount = 0;
        r = 0;
        g = 0;
        b = 0;
        normal = 0;
    }

    public int r() {
        return this.r;
    }

    public int g() {
        return this.g;
    }

    public int b() {
        return this.b;
    }

    public int hitCount() {
        return this.hitCount;
    }

    public double normal() {
        return this.normal;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }

    public void setRGB(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void update(int r, int g, int b) {
        if (this.hitCount == 0) {
            this.r = r;
            this.g = g;
            this.b = b;
        } else {
            this.r = (this.r + r) / 2;
            this.g = (this.g + g) / 2;
            this.b = (this.b + b) / 2;
        }
        this.hitCount++;
    }
}
