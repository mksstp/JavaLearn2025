package edu.project4.Transformations;

import edu.project4.shared.Point;

public class SwirlTransformation implements Transformation {

    public Point apply(Point point, double c, double f) {
        double x = point.x();
        double y = point.y();
        double r = x * x + y * y;
        return new Point(x * Math.sin(r) - y * Math.cos(r), x * Math.cos(r) + y * Math.sin(r));
    }
}
