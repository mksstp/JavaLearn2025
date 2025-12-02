package edu.project4.Transformations;

import edu.project4.shared.Point;

public class HeartTransformation implements Transformation {

    public Point apply(Point point, double c, double f) {
        double x = point.x();
        double y = point.y();
        double help = Math.sqrt(x * x + y * y);
        double help2 = help * Math.atan(y / x);
        double newX = help * Math.sin(help2);
        double newY = -help * Math.cos(help2);
        return new Point(newX, newY);
    }

}
