package edu.project2;

import edu.project2.Cell.Type;
import edu.project2.Generator.ClassicGenerator;
import edu.project2.Generator.GeneratorInterface;
import edu.project2.Generator.KruskalGenerator.KruskalGenerator;
import edu.project2.Solver.SolverBFS;
import edu.project2.Solver.SolverDFS;
import edu.project2.Solver.SolverInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Project2Test {

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
    @DisplayName("Renderer no path test")
    void testRendererNoPath() {

        Maze maze = new Maze(height, width, field);
        Renderer renderer = new Renderer();

        String output = renderer.render(maze);
        String expected = """
            ███████████████
            ███         ███
            ███   ███   ███
            ███   ███   ███
            ███████████████
            """;

        assertEquals(expected, output);
    }

    @Test
    @DisplayName("Renderer with path test")
    void testRenderedPath() {

        Maze maze = new Maze(height, width, field);
        Renderer renderer = new Renderer();

        String output = renderer.render(maze, path);
        String expected =
            """
                ███████████████
                ███ X  X  X ███
                ███   ███ X ███
                ███   ███ X ███
                ███████████████
                """;

        assertEquals(expected, output);
    }

    @Test
    @DisplayName("SolverDFS with no path")
    void testSolverDFSNoPath() {
        Maze maze = new Maze(height, width, fieldNoPath);

        SolverInterface solver = new SolverDFS();
        assertNull(solver.solve(maze, new Coordinate(1, 1), new Coordinate(3, 3)));
    }

    @Test
    @DisplayName("SolverBFS with no path")
    void testSolverBFSNoPath() {
        Maze maze = new Maze(height, width, fieldNoPath);

        SolverInterface solver = new SolverBFS();
        assertNull(solver.solve(maze, new Coordinate(1, 1), new Coordinate(3, 3)));
    }

    @Test
    @DisplayName("SolverDFS with path")
    void testSolverDFS() {
        Maze maze = new Maze(height, width, field);

        SolverInterface solver = new SolverDFS();
        assertEquals(path, solver.solve(maze, new Coordinate(1, 1), new Coordinate(3, 3)));
    }

    @Test
    @DisplayName("SolverBFS with path")
    void testSolverBFS() {
        Maze maze = new Maze(height, width, field);

        SolverInterface solver = new SolverBFS();
        assertEquals(path, solver.solve(maze, new Coordinate(1, 1), new Coordinate(3, 3)));
    }

    private boolean dfs(Maze maze, HashMap<Coordinate, Integer> visited, int i, int j, int fromi, int fromj) {
        visited.put(new Coordinate(i, j), 1);

        List<Coordinate> movements = new ArrayList<>();
        movements.add(new Coordinate(1, 0));
        movements.add(new Coordinate(-1, 0));
        movements.add(new Coordinate(0, 1));
        movements.add(new Coordinate(0, -1));

        for (int k = 0; k < 4; k++) {
            int nexti = i + movements.get(k).row();
            int nextj = j + movements.get(k).col();

            if (maze.getGrid()[nexti][nextj].type() == Type.PASSAGE) {
                if (!visited.containsKey(new Coordinate(nexti, nextj))) {
                    boolean res = dfs(maze, visited, nexti, nextj, i, j);
                    if (!res) {
                        return false;
                    }
                } else if (visited.get(new Coordinate(nexti, nextj)) == 1 && (nexti != fromi || nextj != fromj)) {
                    return false;
                }
            }
        }

        visited.put(new Coordinate(i, j), 2);
        return true;
    }

    private boolean isMazeTreeAndConnected(Maze maze) {
        HashMap<Coordinate, Integer> visited = new HashMap<>();
        int expectedVisitedSz = (maze.getHeight() / 2) * (maze.getWidth() / 2) * 2 - 1;

        return this.dfs(maze, visited, 1, 1, -1, -1)
            && visited.size() == expectedVisitedSz;
    }

    @Test
    @DisplayName("ClassicGenerator test")
    void testClassicGenerator() {
        GeneratorInterface generator = new ClassicGenerator();
        Maze maze = generator.generate(11, 11);
        assertTrue(isMazeTreeAndConnected(maze));
    }

    @Test
    @DisplayName("ClassicGenerator test")
    void testKruskalGenerator() {
        GeneratorInterface generator = new KruskalGenerator();
        Maze maze = generator.generate(11, 11);
        assertTrue(isMazeTreeAndConnected(maze));
    }

    @Test
    @DisplayName("Wrong arguments generate")
    void testGenerator() {
        assertThrows(
            IllegalArgumentException.class, () -> {
                GeneratorInterface generator = new ClassicGenerator();
                Maze maze = generator.generate(10, 11);
            }
        );
    }
}
