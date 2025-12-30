package edu.project2.Generator;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class ClassicGenerator implements GeneratorInterface {

    @Override
    public Maze generateMazeFromTemplate(Maze maze) {
        int height = maze.getHeight();
        int width = maze.getWidth();
        Cell[][] field = maze.getGrid();

        Stack<Coordinate> stack = new Stack<>();
        HashSet<Coordinate> visited = new HashSet<>();
        visited.add(new Coordinate(1, 1));
        stack.push(new Coordinate(1, 1));

        while (!stack.empty()) {
            Coordinate current = stack.pop();
            List<Coordinate> neighbours = getNeighbours(height, width, visited, current);

            if (neighbours.isEmpty()) {
                continue;
            } else if (neighbours.size() > 1) {
                stack.push(current);
            }
            //Breaking wall between current Coordinate and current Neighbour
            Random randomGenerator = new Random();
            int nextIdx = randomGenerator.nextInt(neighbours.size());
            Coordinate next = neighbours.get(nextIdx);
            int wallCoordinateRow = (next.row() + current.row()) / 2;
            int wallCoordinateCol = (next.col() + current.col()) / 2;
            field[wallCoordinateRow][wallCoordinateCol] =
                new Cell(wallCoordinateRow, wallCoordinateCol, Cell.Type.PASSAGE);
            visited.add(next);
            stack.push(next);
        }

        return new Maze(height, width, field);
    }
}
