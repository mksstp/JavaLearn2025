package edu.project1;

import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrintStatus {
    private PrintStatus() {
    }

    private final static Logger LOGGER = LogManager.getLogger();

    public static void output(Game game, UserWord userWord) {
        LOGGER.info("\n{}", PICTURE[game.getCurrErrors()]);
        LOGGER.info(Arrays.toString((userWord.getUserWord())));
        LOGGER.info("Ошибок : {} из {}", game.getCurrErrors(), game.getMaxErrors());
        LOGGER.info("Использованные неправильные буквы : {}", game.getWrongLetters());
        LOGGER.info("Введите exit, чтобы сдаться");
    }

    public static final String[] PICTURE = {"""
    _____
    |   |
    |
    |
    |
    |\
""", """
    _____
    |   |
    |   O
    |
    |
    |\
""", """
    _____
    |   |
    |   O
    |   |
    |
    |\
""", """
    _____
    |   |
    |   O
    |  /|
    |
    |\
""", """
    _____
    |   |
    |   O
    |  /|\\
    |
    |\
""", """
    _____
    |   |
    |   O
    |  /|\\
    |  / \\
    |\
"""};
}
