package edu.project4.Transformations;

import edu.project4.shared.Point;

public class SphereTransformation implements Transformation {

    public Point apply(Point point, double c, double f) {
        double x = point.x();
        double y = point.y();
        double zn = x * x + y * y;
        return new Point(x / zn, y / zn);
    }
}
