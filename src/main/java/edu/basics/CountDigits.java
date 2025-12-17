package edu.basics;

public final class CountDigits {
    private CountDigits() {
    }

    private static final int TEN = 10;

    public static int countDigits(Integer checkNumber) {
        int countDigits = 0;
        Integer number = checkNumber;
        if (number.equals(0)) {
            return 1;
        }

        while (number != 0) {
            number = number / TEN;
            countDigits++;
        }
        return countDigits;
    }
}
