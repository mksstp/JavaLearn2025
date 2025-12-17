package edu.basics;

public final class ByteShift {
    private ByteShift() {
    }

    private static final String UNCORRECT_INPUT = "Введено некорректное число";

    private static boolean checkNumber(int checkNumber) {
        return checkNumber <= 0;
    }

    public static int rotateLeft(int n, int shift) {
        if (checkNumber(n) || checkNumber(shift)) {
            throw new IllegalArgumentException(UNCORRECT_INPUT);
        }
        StringBuilder numberInBinary = new StringBuilder(Integer.toBinaryString(n));
        int realShift = shift;
        if (realShift > numberInBinary.length()) {
            realShift = numberInBinary.length() % realShift;
        }
        return Integer.parseInt(numberInBinary.substring(realShift) + numberInBinary.substring(0, realShift), 2);
    }

    public static int rotateRight(int n, int shift) {
        if (checkNumber(n) || checkNumber(shift)) {
            throw new IllegalArgumentException(UNCORRECT_INPUT);
        }
        return (n >>> shift) | (n << (Integer.SIZE - shift));
    }
}
