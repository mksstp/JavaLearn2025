package edu.basics5;

public final class Task6 {
    private Task6() {
    }

    public static boolean subChecker(String subSeq, String sequence) {
        char[] charSubSeq = subSeq.toCharArray();
        StringBuilder pattern = new StringBuilder();
        pattern.append(".*");
        for (char symbol : charSubSeq) {
            pattern.append("[");
            if (symbol == '\\') {
                pattern.append('\\');
            }
            pattern.append(symbol);
            pattern.append("]");
            pattern.append(".*");
        }
        return sequence.matches(pattern.toString());
    }
}
