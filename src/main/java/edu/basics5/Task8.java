package edu.basics5;

public final class Task8 {
    private Task8() {
    }

    public static boolean validateOddLength(String inputString) {
        return inputString.matches("^[01]([01]{2})*$");
    }

    public static boolean validateZeroOddOrOneEven(String inputString) {
        return inputString.matches("^0([01]{2})*|1[01]([01]{2})*$");
    }

    public static boolean validateCountZeroDividedByThree(String inputString) {
        return inputString.matches("^(1*01*01*0)*1*$");
    }

    public static boolean validateException11and111(String inputString) {
        return inputString.matches("^(?!11$|111$)[01]+$");
    }

    public static boolean validateOddEqualsOne(String inputString) {
        return inputString.matches("^1([01]($|1))*$");
    }

    public static boolean validateMin2ZerosAndMax1One(String inputString) {
        return inputString.matches("(^00+10*$)|(^0+10+$)|(^100+$)");
    }

    public static boolean validateNoneSeqOfOne(String inputString) {
        return inputString.matches("^(?![01]*11[01]*)[01]+$");
    }
}
