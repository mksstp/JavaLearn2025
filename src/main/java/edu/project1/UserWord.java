package edu.project1;

import java.util.Arrays;

public class UserWord {
    public UserWord(String secretWord) {
        this.secretWord = secretWord;
        this.userWord = new char[secretWord.length()];
        Arrays.fill(userWord, '*');
    }

    private final String secretWord;
    private final char[] userWord;

    public String getSecretWord() {
        return secretWord;
    }

    public char[] getUserWord() {
        return userWord;
    }

    public boolean isContainsLetter(String letter) {
        return secretWord.contains(letter);
    }

    public void openLetter(String letter) {
        int indexOpenLetter = secretWord.indexOf(letter);
        while (-1 != indexOpenLetter) {
            userWord[indexOpenLetter] = letter.charAt(0);
            indexOpenLetter = secretWord.indexOf(letter, indexOpenLetter + 1);
        }
    }
}
