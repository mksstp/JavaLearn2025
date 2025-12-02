package edu.project4.Transformations;

import edu.project4.shared.Point;

public class PopcornTransformation implements Transformation {

    @SuppressWarnings("MagicNumber")
    public Point apply(Point point, double c, double f) {
        double x = point.x();
        double y = point.y();
        return new Point(
            x + c * Math.sin(Math.tan(3 * y)),
            y + f * Math.sin(Math.tan(3 * x))
        );
    }
}
