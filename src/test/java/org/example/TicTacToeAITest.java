package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeAITest {

    @Test
    public void testComputerWinsWhenPossible() {
        char[][] board = {
                {'X', 'X', ' '},
                {'O', 'O', ' '},
                {' ', ' ', ' '}
        };
        int[] move = TicTacToeAI.getBestMove(board, 'X', 'O');
        assertArrayEquals(new int[]{0, 2}, move, "Computer should place X to win");
    }



//    @Test
//    public void testComputerBlocksWin() {
//        char[][] board = {
//                {'O', 'O', ' '},  // Opponent "O" is about to win by placing at [0, 2]
//                {'X', 'X', ' '},  // AI "X" on the second row
//                {' ', ' ', ' '}
//        };
//
//        // Log the board before the AI move
//        System.out.println("Initial Board:");
//        printBoard(board);
//
//        // Call the AI move function
//        int[] move = TicTacToeAI.getBestMove(board, 'X', 'O');
//
//        // Print the move decision for debugging
//        System.out.println("AI Move: [" + move[0] + ", " + move[1] + "]");
//
//        // Check the expected blocking move
//        assertArrayEquals(new int[]{0, 2}, move, "Computer should block O from winning");
//    }



    @Test
    public void testComputerTakesCenter() {
        char[][] board = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };
        int[] move = TicTacToeAI.getBestMove(board, 'X', 'O');
        assertArrayEquals(new int[]{1, 1}, move, "Computer should take center when available");
    }

    @Test
    public void testComputerTakesCorner() {
        char[][] board = {
                {' ', 'O', ' '},
                {' ', 'X', ' '},
                {' ', ' ', ' '}
        };
        int[] move = TicTacToeAI.getBestMove(board, 'X', 'O');
        assertTrue((move[0] == 0 && move[1] == 0) ||
                (move[0] == 0 && move[1] == 2) ||
                (move[0] == 2 && move[1] == 0) ||
                (move[0] == 2 && move[1] == 2), "Computer should take a corner");
    }

    @Test
    public void testComputerTakesSideWhenNoBetterOption() {
        char[][] board = {
                {'X', 'O', 'X'},
                {'X', 'O', 'O'},
                {'O', ' ', 'X'}
        };
        int[] move = TicTacToeAI.getBestMove(board, 'X', 'O');
        assertArrayEquals(new int[]{2, 1}, move, "Computer should take the only available side");
    }

    private static void printBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
        System.out.println();  // Blank line between board states
    }

}

