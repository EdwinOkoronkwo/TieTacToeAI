package org.example;

public class TicTacToeAI {

    public static int[] getBestMove(char[][] board, char computerMarker, char playerMarker) {
        int[] move;

        // 1. Check if AI can win
        move = findWinningMove(board, computerMarker);
        if (move != null) return move;

        // 2. Check if AI needs to block opponent from winning
        move = findWinningMove(board, playerMarker);
        if (move != null) return move;

        // 3. Take center if available
        if (board[1][1] == ' ') return new int[]{1, 1};

        // 4. Take a random available corner
        move = getAvailableCorner(board);
        if (move != null) return move;

        // 5. Take any available side
        return getAvailableSide(board);
    }

    private static int[] findWinningMove(char[][] board, char marker) {
        // Step 1: Check if the opponent (O) is about to win and block it
        for (int i = 0; i < 3; i++) {
            // First, check if there's a potential win in the row
            if (checkRow(board, i, marker)) {
                System.out.println("Blocking win at row: " + i);
                return new int[]{i, getEmptyIndex(board[i])}; // Block the win in the row
            }
            // Then check if there's a potential win in the column
            if (checkColumn(board, i, marker)) {
                System.out.println("Blocking win at column: " + i);
                return new int[]{getEmptyIndex(getColumn(board, i)), i}; // Block the win in the column
            }
        }

        // Step 2: Check diagonals for a potential win
        if (checkMainDiagonal(board, marker)) {
            System.out.println("Blocking win on main diagonal.");
            int emptyIndex = getEmptyIndex(getMainDiagonal(board));
            return new int[]{emptyIndex, emptyIndex}; // Block the win on the main diagonal
        }
        if (checkAntiDiagonal(board, marker)) {
            System.out.println("Blocking win on anti-diagonal.");
            int emptyIndex = getEmptyIndex(getAntiDiagonal(board));
            return new int[]{emptyIndex, 2 - emptyIndex}; // Block the win on the anti-diagonal
        }

        return null; // No winning move found
    }







    private static int[] getAvailableCorner(char[][] board) {
        int[][] corners = {{0, 0}, {0, 2}, {2, 0}, {2, 2}};
        for (int[] corner : corners) {
            if (board[corner[0]][corner[1]] == ' ') return corner;
        }
        return null;
    }

    private static int[] getAvailableSide(char[][] board) {
        int[][] sides = {{0, 1}, {1, 0}, {1, 2}, {2, 1}};
        for (int[] side : sides) {
            if (board[side[0]][side[1]] == ' ') return side;
        }
        return null;
    }

    private static boolean checkRow(char[][] board, int row, char marker) {
        int count = 0;
        for (char cell : board[row]) {
            if (cell == marker) count++;
        }
        return count == 2 && getEmptyIndex(board[row]) != -1;
    }

    private static boolean checkColumn(char[][] board, int col, char marker) {
        char[] column = getColumn(board, col);
        int count = 0;
        for (char cell : column) {
            if (cell == marker) count++;
        }
        return count == 2 && getEmptyIndex(column) != -1;
    }

    private static char[] getColumn(char[][] board, int col) {
        return new char[]{board[0][col], board[1][col], board[2][col]};
    }

    private static char[] getMainDiagonal(char[][] board) {
        return new char[]{board[0][0], board[1][1], board[2][2]};
    }

    private static char[] getAntiDiagonal(char[][] board) {
        return new char[]{board[0][2], board[1][1], board[2][0]};
    }

    private static boolean checkMainDiagonal(char[][] board, char marker) {
        char[] diagonal = getMainDiagonal(board);
        int count = 0;
        for (char cell : diagonal) {
            if (cell == marker) count++;
        }
        return count == 2 && getEmptyIndex(diagonal) != -1;
    }

    private static boolean checkAntiDiagonal(char[][] board, char marker) {
        char[] diagonal = getAntiDiagonal(board);
        int count = 0;
        for (char cell : diagonal) {
            if (cell == marker) count++;
        }
        return count == 2 && getEmptyIndex(diagonal) != -1;
    }

    private static int getEmptyIndex(char[] line) {
        for (int i = 0; i < line.length; i++) {
            if (line[i] == ' ') return i;
        }
        return -1;
    }
}
