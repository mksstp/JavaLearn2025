package edu.basics3;

import java.util.ArrayList;
import java.util.List;

public final class Task2 {
    private Task2() {
    }

    private static boolean checkString(String processedString) {
        if ((processedString == null) || processedString.isEmpty() || ((processedString.length() % 2) != 0)) {
            return true;
        } else {
            char[] chArr = processedString.toCharArray();
            for (char c : chArr) {
                if (!(c == '(' || c == ')')) {
                    return true;
                }
            }
            return false;
        }
    }

    public static List<String> clusterize(String message) {
        if (checkString(message)) {
            throw new IllegalArgumentException("Введена некорректная строка!");
        }
        List<String> clusterizeMessage = new ArrayList<>();
        StringBuilder cluster = new StringBuilder();
        int count = 0;
        for (char symbol : message.toCharArray()) {
            if (symbol == '(') {
                cluster.append(symbol);
                count++;
            } else if (symbol == ')') {
                cluster.append(symbol);
                count--;
            }
            if (count == 0) {
                clusterizeMessage.add(cluster.toString());
                cluster.delete(0, cluster.length());
            }
        }
        if (count != 0) {
            throw new IllegalArgumentException("Не у всех скобок есть пара");
        }
        return clusterizeMessage;
    }
}
