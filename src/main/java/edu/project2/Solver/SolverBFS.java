package edu.project2.Solver;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;

public class SolverBFS implements SolverInterface {

    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        Queue<Coordinate> queue = new ArrayDeque<>();
        HashSet<Coordinate> visited = new HashSet<>();
        HashMap<Coordinate, Coordinate> parent = new HashMap<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();

            Coordinate leftNeighbour = new Coordinate(current.row(), current.col() - 1);
            Coordinate rightNeighbour = new Coordinate(current.row(), current.col() + 1);
            Coordinate highNeighbour = new Coordinate(current.row() + 1, current.col());
            Coordinate lowNeighbour = new Coordinate(current.row() - 1, current.col());

            if (maze.getGrid()[current.row() + 1][current.col()].type() == Cell.Type.PASSAGE
                && !visited.contains(highNeighbour)) {
                parent.put(highNeighbour, current);
                visited.add(highNeighbour);
                queue.add(highNeighbour);
            }

            if (maze.getGrid()[current.row() - 1][current.col()].type() == Cell.Type.PASSAGE
                && !visited.contains(lowNeighbour)) {
                parent.put(lowNeighbour, current);
                visited.add(lowNeighbour);
                queue.add(lowNeighbour);
            }

            if (maze.getGrid()[current.row()][current.col() + 1].type() == Cell.Type.PASSAGE
                && !visited.contains(rightNeighbour)) {
                parent.put(rightNeighbour, current);
                visited.add(rightNeighbour);
                queue.add(rightNeighbour);
            }

            if (maze.getGrid()[current.row()][current.col() - 1].type() == Cell.Type.PASSAGE
                && !visited.contains(leftNeighbour)) {
                parent.put(leftNeighbour, current);
                visited.add(leftNeighbour);
                queue.add(leftNeighbour);
            }
        }

        if (!visited.contains(end)) {
            return null;
        }

        return restorePath(start, end, parent);
    }
}
