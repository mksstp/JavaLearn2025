package edu.project1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dictionary {
    private final List<String> listWords;

    public Dictionary(String filename) {
        InputStream resource = this.getClass().getClassLoader().getResourceAsStream(filename);
        if (resource == null) {
            throw new RuntimeException("Файл " + filename + " не найден");
        }
        listWords = new BufferedReader(new InputStreamReader(resource)).lines().toList();
    }

    public String getRandomWord(int minLength, int maxLength) {
        List<Integer> indexOfCorrectWords = new ArrayList<>();
        for (int i = 0; i < listWords.size(); i++) {
            if ((listWords.get(i).length() >= minLength) && (listWords.get(i).length() <= maxLength)
                && !listWords.get(i).contains("-")) {
                indexOfCorrectWords.add(i);
            }
        }
        if (indexOfCorrectWords.isEmpty()) {
            throw new RuntimeException("Нет слов заданной длины,"
                + "требуется обновить словарь или выбрать другую длину.");
        }
        return listWords.get(indexOfCorrectWords.get(new Random().nextInt(indexOfCorrectWords.size())));
    }
}
