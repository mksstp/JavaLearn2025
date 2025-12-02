package edu.project4.Transformations;

import edu.project4.shared.Point;

public class SinusoidTransformation implements Transformation {


    public Point apply(Point point, double c, double f) {
        return new Point(Math.sin(point.x()), Math.sin(point.y()));
    }

}
