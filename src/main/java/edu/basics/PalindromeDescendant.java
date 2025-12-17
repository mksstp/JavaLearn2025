package edu.basics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class PalindromeDescendant {
    private PalindromeDescendant() {
    }

    private static boolean checkPalindrome(char[] string) {
        int border = string.length / 2;
        boolean status = true;
        for (int i = 0; i < border; i++) {
            if (string[i] != string[string.length - 1 - i]) {
                status = false;
                break;
            }
        }
        return status;
    }

    private static String createChild(String parent) {
        char[] parentCharArr = parent.toCharArray();
        List<Integer> childList = new ArrayList<>();

        for (int i = 0; i < parentCharArr.length; i = i + 2) {
            childList.add(
                Character.getNumericValue(parentCharArr[i]) + Character.getNumericValue(parentCharArr[i + 1]));
        }
        return childList.stream().map(String::valueOf)
            .collect(Collectors.joining(""));

    }

    public static boolean isPalindromeDescendant(String testString) {
        if (testString.isEmpty()) {
            throw new IllegalArgumentException("Пустая строка!");
        }
        if (checkPalindrome(testString.toCharArray())) {
            return true;
        }
        if ((testString.length() >= 2) && testString.length() % 2 == 0) {
            String child = createChild(testString);
            return isPalindromeDescendant(child);
        }
        return false;
    }
}
