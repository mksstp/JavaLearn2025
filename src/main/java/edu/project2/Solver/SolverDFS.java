package edu.project2.Solver;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class SolverDFS implements SolverInterface {

    Stack<Coordinate> stack = new Stack<>();
    HashSet<Coordinate> visited = new HashSet<>();
    HashMap<Coordinate, Coordinate> parent = new HashMap<>();

    private void dfs(Maze maze, Coordinate current, Coordinate end) {
        visited.add(current);

        // проходимся по соседям
        Coordinate leftNeighbour = new Coordinate(current.row(), current.col() - 1);
        Coordinate rightNeighbour = new Coordinate(current.row(), current.col() + 1);
        Coordinate highNeighbour = new Coordinate(current.row() + 1, current.col());
        Coordinate lowNeighbour = new Coordinate(current.row() - 1, current.col());
        if (maze.getGrid()[current.row() + 1][current.col()].type() == Cell.Type.PASSAGE
            && !visited.contains(highNeighbour)) {
            parent.put(highNeighbour, current);
            dfs(maze, highNeighbour, end);
        }

        if (maze.getGrid()[current.row() - 1][current.col()].type() == Cell.Type.PASSAGE
            && !visited.contains(lowNeighbour)) {
            parent.put(lowNeighbour, current);
            dfs(maze, lowNeighbour, end);
        }

        if (maze.getGrid()[current.row()][current.col() + 1].type() == Cell.Type.PASSAGE
            && !visited.contains(rightNeighbour)) {
            parent.put(rightNeighbour, current);
            dfs(maze, rightNeighbour, end);
        }

        if (maze.getGrid()[current.row()][current.col() - 1].type() == Cell.Type.PASSAGE
            && !visited.contains(leftNeighbour)) {
            parent.put(leftNeighbour, current);
            dfs(maze, leftNeighbour, end);
        }
    }

    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        stack.push(start);
        visited.add(start);

        dfs(maze, start, end);

        if (!visited.contains(end)) {
            return null;
        }

        return restorePath(start, end, parent);
    }
}
