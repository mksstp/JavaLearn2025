package edu.hw9.Task3;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import edu.project2.Type;
import edu.project2.solver.SolverInterface;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MultiThreadDFSSolverTest {

    static int height = 5;
    static int width = 5;
    static Cell[][] field = new Cell[height][width];
    static Cell[][] fieldNoPath = new Cell[height][width];
    static List<Coordinate> path = new ArrayList<>();

    @BeforeAll
    static void beforeAll() {
        for (int i = 0; i < height; i += 1) {
            for (int j = 0; j < width; j += 1) {
                if (i % 2 == 0 || j % 2 == 0) {
                    field[i][j] = new Cell(i, j, Type.WALL);
                    fieldNoPath[i][j] = new Cell(i, j, Type.WALL);
                } else {
                    field[i][j] = new Cell(i, j, Type.PASSAGE);
                    fieldNoPath[i][j] = new Cell(i, j, Type.WALL);
                }
            }
        }
        field[1][2] = new Cell(1, 2, Type.PASSAGE);
        field[2][1] = new Cell(2, 1, Type.PASSAGE);
        field[2][3] = new Cell(2, 3, Type.PASSAGE);
        fieldNoPath[1][2] = new Cell(1, 2, Type.PASSAGE);
        fieldNoPath[2][1] = new Cell(2, 1, Type.PASSAGE);

        path.add(new Coordinate(1, 1));
        path.add(new Coordinate(1, 2));
        path.add(new Coordinate(1, 3));
        path.add(new Coordinate(2, 3));
        path.add(new Coordinate(3, 3));
    }


    @Test
    @DisplayName("SolverDFS with no path")
    void testSolverDFSNoPath() {
        Maze maze = new Maze(height, width, fieldNoPath);

        SolverInterface solver = new MultiThreadDFSSolver();
        assertNull(solver.solve(maze, new Coordinate(1, 1), new Coordinate(3, 3)));
    }

    @Test
    @DisplayName("SolverDFS with path")
    void testSolverDFS() {
        Maze maze = new Maze(height, width, field);

        SolverInterface solver = new MultiThreadDFSSolver();
        assertEquals(path, solver.solve(maze, new Coordinate(1, 1), new Coordinate(3, 3)));
    }
}
