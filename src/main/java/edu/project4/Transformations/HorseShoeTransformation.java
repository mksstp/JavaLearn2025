package edu.project4.Transformations;

import edu.project4.shared.Point;
import static java.lang.Math.sqrt;

public class HorseShoeTransformation implements Transformation {

    public Point apply(Point point, double c, double f) {
        double x = point.x();
        double y = point.y();
        double r = sqrt(x * x + y * y);

        return new Point((x - y) * (x + y) / r, 2 * x * y / r);
    }
}
