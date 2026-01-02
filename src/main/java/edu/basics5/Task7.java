package edu.basics5;

public final class Task7 {
    private Task7() {
    }

    public static boolean validateThirdZero(String inputString) {
        return inputString.matches("^[01]{2}0[01]*$");
    }

    public static boolean validateStartEqualsEnd(String inputString) {
        return inputString.matches("^([01])[01]*\\1$");
    }

    public static boolean validateLengthOneToThree(String inputString) {
        return inputString.matches("^[01]{1,3}$");
    }
}
