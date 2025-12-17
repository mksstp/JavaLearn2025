package edu.basics;

public class BrokenString {
    private BrokenString() {
    }

    public static String fixString(String brokenString) {
        char[] charArray = brokenString.toCharArray();
        int border = charArray.length;
        if (border % 2 != 0) {
            border--;
        }
        for (int i = 0; i < border; i = i + 2) {
            char swapper = charArray[i];
            charArray[i] = charArray[i + 1];
            charArray[i + 1] = swapper;
        }
        return String.valueOf(charArray);
    }
}
