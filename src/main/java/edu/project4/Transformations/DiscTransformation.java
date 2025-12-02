package edu.project4.Transformations;

import edu.project4.shared.Point;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

public class DiscTransformation implements Transformation {

    public Point apply(Point point, double c, double f) {
        double x = point.x();
        double y = point.y();
        double r = sqrt(x * x + y * y);

        double newX = 1 / Math.PI * Math.atan(y / x) * sin(Math.PI * r);
        double newY = 1 / Math.PI * Math.atan(y / x) * cos(Math.PI * r);
        return new Point(newX, newY);
    }

}
