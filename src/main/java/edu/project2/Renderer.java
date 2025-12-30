package edu.project2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Renderer {

    private String renderHelper(Maze maze, HashSet<Coordinate> onPath) {
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < maze.getHeight(); i += 1) {
            for (int j = 0; j < maze.getWidth(); j += 1) {
                if (maze.getGrid()[i][j].type() == Cell.Type.PASSAGE) {
                    if (onPath.contains(new Coordinate(i, j))) {
                        list.add(' ');
                        list.add('X');
                        list.add(' ');
                    } else {
                        list.add(' ');
                        list.add(' ');
                        list.add(' ');
                    }
                } else {
                    list.add('█');
                    list.add('█');
                    list.add('█');
                }
            }
            list.add('\n');
        }
        return list.stream()
            .map(String::valueOf)
            .collect(Collectors.joining());
    }

    public String render(Maze maze) {
        HashSet<Coordinate> onPath = new HashSet<>();
        return this.renderHelper(maze, onPath);
    }

    public String render(Maze maze, List<Coordinate> path) {
        HashSet<Coordinate> onPath = new HashSet<>(path);
        return this.renderHelper(maze, onPath);
    }
}
