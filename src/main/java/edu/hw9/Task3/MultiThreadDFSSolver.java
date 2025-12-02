package edu.hw9.Task3;

import edu.project2.Coordinate;
import edu.project2.Maze;
import edu.project2.Type;
import edu.project2.solver.SolverInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiThreadDFSSolver implements SolverInterface {
    final Stack<Coordinate> stack = new Stack<>();
    final HashSet<Coordinate> visited = new HashSet<>();
    final HashMap<Coordinate, Coordinate> parent = new HashMap<>();

    private final int directionsCount = 4;
    private final int nThreads = 5;

    private void dfs(Maze maze, Coordinate current) {
        synchronized (visited) {
            if (visited.contains(current)) {
                return;
            }
            visited.add(current);
        }

        List<Coordinate> movements = new ArrayList<>();
        movements.add(new Coordinate(1, 0));
        movements.add(new Coordinate(-1, 0));
        movements.add(new Coordinate(0, 1));
        movements.add(new Coordinate(0, -1));

        int i = current.row();
        int j = current.col();
        Coordinate curFlowTo = null;
        boolean flag = false;
        for (int k = 0; k < directionsCount; k++) {
            int nexti = i + movements.get(k).row();
            int nextj = j + movements.get(k).col();

            if (maze.getGrid()[nexti][nextj].type() == Type.PASSAGE) {
                Coordinate to = new Coordinate(nexti, nextj);
                boolean visitedContains;
                synchronized (visited) {
                    visitedContains = visited.contains(to);
                }

                if (!visitedContains) {
                    if (!flag) {
                        flag = true;
                        curFlowTo = to;
                        synchronized (parent) {
                            parent.put(curFlowTo, current);
                        }
                    } else {
                        synchronized (stack) {
                            stack.push(to);
                        }
                        synchronized (parent) {
                            parent.put(to, current);
                        }
                    }
                }
            }
            if (curFlowTo != null) {
                dfs(maze, curFlowTo);
            }
        }
    }

    private void dfs(Maze maze) {
        Coordinate current;
        synchronized (stack) {
            if (stack.empty()) {
                return;
            }
            current = stack.pop();
        }
        dfs(maze, current);
    }

    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        stack.push(start);

        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);

        List<Future> futuresList = new ArrayList<>();
        for (int i = 0; i < nThreads; i++) {
            futuresList.add(executorService.submit(() -> dfs(maze)));
        }

        while (true) {
            boolean flag = false;

            for (int i = 0; i < nThreads; i++) {
                if (!futuresList.get(i).isDone()) {
                    flag = true;
                } else {
                    futuresList.set(i, executorService.submit(() -> dfs(maze)));
                }
            }

            if (!flag) {
                break;
            }
        }

        executorService.shutdown();

        if (!visited.contains(end)) {
            return null;
        }

        return restorePath(start, end, parent);
    }
}
