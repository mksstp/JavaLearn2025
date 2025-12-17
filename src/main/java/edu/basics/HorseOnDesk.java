package edu.basics;

public final class HorseOnDesk {
    private HorseOnDesk() {
    }

    private static final int STANDARTLENGTH = 8;
    private static final String UNCORRECT_INPUT = "Введена некорректная доска!";
    private static final int[][] STEPS = {
        {1, 2},
        {2, 1},
        {-1, 2},
        {-2, 1},
        {1, -2},
        {2, -1},
        {-1, -2},
        {-2, -1}};

    private static Boolean checkBoard(Integer[][] checkBoard) {
        if (checkBoard == null || checkBoard.length != STANDARTLENGTH) {
            return true;
        }
        for (int i = 0; i < STANDARTLENGTH; i++) {
            if (checkBoard[i] == null || checkBoard[i].length != STANDARTLENGTH) {
                return true;
            }
            for (int j = 0; j < STANDARTLENGTH; j++) {
                if (checkBoard[i][j] == null || !(checkBoard[i][j].equals(0) || checkBoard[i][j].equals(1))) {
                    return true;
                }
            }
        }
        return false;
    }

    private static Boolean checkEnvironment(int xPos, int yPos, Integer[][] board) {
        for (int i = 0; i < STANDARTLENGTH; i++) {
            int currentX = xPos + STEPS[i][0];
            int currentY = yPos + STEPS[i][1];
            if ((currentX >= 0) && (currentX < STANDARTLENGTH) && (currentY >= 0) && (currentY < STANDARTLENGTH)) {
                if (board[currentX][currentY].equals(1)) {
                    return true;
                }
            }
        }
        return false;

    }

    public static Boolean knightBoardCapture(Integer[][] board) {
        if (checkBoard(board)) {
            throw new IllegalArgumentException(UNCORRECT_INPUT);
        }
        for (int i = 0; i < STANDARTLENGTH; i++) {
            for (int j = 0; j < STANDARTLENGTH; j++) {
                if (board[i][j].equals(1)) {
                    if (checkEnvironment(i, j, board)) {
                        return false;
                    }
                }

            }
        }
        return true;
    }
}
