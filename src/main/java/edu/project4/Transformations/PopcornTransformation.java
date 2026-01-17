package edu.project4.Transformations;

import edu.project4.shared.Point;
import static java.lang.Math.sin;
import static java.lang.Math.tan;

public class PopcornTransformation implements Transformation {

    @Override
    @SuppressWarnings("MagicNumber")
    public Point apply(Point point, double c, double f) {
        double x = point.x();
        double y = point.y();
        return new Point(
            x + c * sin(tan(3 * y)),
            y + f * sin(tan(3 * x))
        );
    }
}
