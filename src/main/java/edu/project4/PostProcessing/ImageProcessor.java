package edu.project4.PostProcessing;

import edu.project4.shared.FractalImage;

@FunctionalInterface
public interface ImageProcessor {

    void process(FractalImage image);
}
