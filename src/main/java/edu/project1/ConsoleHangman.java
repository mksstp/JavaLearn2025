package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ConsoleHangman {
    private ConsoleHangman() {
    }

    public static final Logger LOGGER = LogManager.getLogger();
    private static final Scanner INPUT = new Scanner(System.in);
    private static final String FILENAME = "russianWords.txt";
    private static final Dictionary DICTIONARY = new Dictionary(FILENAME);
    private static final int MIN_WORD_LENGTH = 2;
    private static final int MAX_WORD_LENGTH = 10;

    public static void consoleHangman() {
        LOGGER.info("Добро пожаловать в виселицу!");
        while (true) {
            LOGGER.info("Выберите действие :\n1 : Новая игра\n2 : Выйти из игры");
            switch (INPUT.next()) {
                case ("1") -> {
                    Game game = new Game(DICTIONARY.getRandomWord(MIN_WORD_LENGTH, MAX_WORD_LENGTH));
                    game.startGame();
                }
                case ("2") -> {
                    LOGGER.info("До новой встречи!");
                    System.exit(0);
                }
                default -> LOGGER.info("Введена некорректная команда!");
            }
        }
    }
}
