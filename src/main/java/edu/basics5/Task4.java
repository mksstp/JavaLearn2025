package edu.basics5;

public final class Task4 {
    private Task4() {
    }

    public static boolean passwordChecker(String password) {
        return password.matches(".*[~!@#$%^&*|].*");
    }
}
