package edu.project2.Generator;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public interface GeneratorInterface {

    int MINCOORDINATE = 1;
    int STEP = 2;

    private Maze generateTemplate(int height, int width) {
        Cell[][] field = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i % 2 == 0 || j % 2 == 0) {
                    field[i][j] = new Cell(i, j, Cell.Type.WALL);
                } else {
                    field[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
                }
            }
        }
        return new Maze(height, width, field);
    }

    default Maze generate(int height, int width) {
        if (height % 2 == 0 || width % 2 == 0) {
            throw new IllegalArgumentException("Arguments must be odd!");
        }
        Maze maze = generateTemplate(height, width);

        return generateMazeFromTemplate(maze);
    }

    Maze generateMazeFromTemplate(Maze maze);

    default List<Coordinate> getNeighbours(int height, int width, HashSet<Coordinate> visited, Coordinate current) {
        List<Coordinate> neighbours = new ArrayList<>();
        if (current.row() >= MINCOORDINATE + STEP) {
            Coordinate lowNeighbour = new Coordinate(current.row() - STEP, current.col());
            if (!visited.contains(lowNeighbour)) {
                neighbours.add(lowNeighbour);
            }
        }

        if (current.col() >= MINCOORDINATE + STEP) {
            Coordinate leftNeighbour = new Coordinate(current.row(), current.col() - STEP);
            if (!visited.contains(leftNeighbour)) {
                neighbours.add(leftNeighbour);
            }
        }

        if (current.row() <= height - 1 - MINCOORDINATE - STEP) {
            Coordinate highNeighbour = new Coordinate(current.row() + STEP, current.col());
            if (!visited.contains(highNeighbour)) {
                neighbours.add(highNeighbour);
            }
        }

        if (current.col() <= width - 1 - MINCOORDINATE - STEP) {
            Coordinate rightNeighbour = new Coordinate(current.row(), current.col() + STEP);
            if (!visited.contains(rightNeighbour)) {
                neighbours.add(rightNeighbour);
            }
        }
        return neighbours;
    }
}
