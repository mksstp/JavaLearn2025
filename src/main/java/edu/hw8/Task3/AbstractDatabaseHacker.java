package edu.hw8.Task3;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractDatabaseHacker implements DatabaseHacker {

    protected static final int MIN_PASSWORD_LENGTH = 4;
    protected static final int MAX_PASSWORD_LENGTH = 6;
    protected static final String ALF = "qwertyuiopasdfghjklzxcvbnm0123456789";
    protected final List<Character> alfCharacters;

    public AbstractDatabaseHacker() {
        this.alfCharacters = ALF.chars().mapToObj((c) -> (char) c).collect(Collectors.toList());
    }
}
