package edu.project1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Game {
    private int currErrors = 0;
    private static final int MAX_ERRORS = 5;
    private final List<String> wrongLetters = new ArrayList<>();
    private final UserWord userWord;
    private static final Scanner INPUT = new Scanner(System.in);
    private static final Logger LOGGER = LogManager.getLogger();

    public Game(String secretWord) {
        userWord = new UserWord(secretWord);
    }

    private boolean isCorrectLetter(String letter) {
        return (letter.charAt(0) >= 'а' && letter.charAt(0) <= 'я') && !wrongLetters.contains(letter);
    }

    public List<String> getWrongLetters() {
        return wrongLetters;
    }

    public int getCurrErrors() {
        return currErrors;
    }

    public int getMaxErrors() {
        return MAX_ERRORS;
    }

    private void processingLetter(String letter) {
        if (userWord.isContainsLetter(letter)) {
            userWord.openLetter(letter);
        } else {
            wrongLetters.add(letter);
            currErrors++;
        }
    }

    private boolean checkWin(String secretWord, char[] userWord) {
        return secretWord.equals(String.valueOf(userWord));
    }

    public void startGame() {
        PrintStatus.output(this, userWord);
        do {
            String input = INPUT.next();
            if (input.equals("exit")) {
                break;
            } else {
                String letter = (String.valueOf(input.charAt(0))).toLowerCase();
                if (isCorrectLetter(letter)) {
                    processingLetter(letter);
                } else {
                    LOGGER.info("Введите корректную букву!");
                }
                PrintStatus.output(this, userWord);
            }
        } while (currErrors < MAX_ERRORS && !checkWin(userWord.getSecretWord(), userWord.getUserWord()));
        if (checkWin(userWord.getSecretWord(), userWord.getUserWord())) {
            LOGGER.info("Мои поздравления, вы полностью отгадали слово : {}", userWord.getSecretWord());
        } else {
            LOGGER.info("К сожалению, вы проиграли, загаданное слово : {}", userWord.getSecretWord());
        }
    }
}
