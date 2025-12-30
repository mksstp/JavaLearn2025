package edu.project2.Solver;

import edu.project2.Coordinate;
import edu.project2.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public interface SolverInterface {

    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);


    default List<Coordinate> restorePath(Coordinate start, Coordinate end, HashMap<Coordinate, Coordinate> parent) {
        List<Coordinate> way = new ArrayList<>();
        Coordinate current = end;
        while (!current.equals(start)) {
            way.add(current);
            current = parent.get(current);
        }
        way.add(start);

        Collections.reverse(way);
        return way;
    }
}
