package edu.project4.Transformations;

import edu.project4.shared.Point;

public interface Transformation {

    Point apply(Point point, double c, double f);
}
