package com.example.ijsudoku;

import java.util.Random;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    private static final int N = 9;
    private static char[][] sud_problem;
    private static Random random = new Random();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }



    public static String DoSolve() {

        // Löst das Sudoku
        solveSudoku(sud_problem);

        // Gibt das gelöste Sudoku aus
        printSolution();
        return printUI();
    }

    public static void fillRandomNumbers(char[][] board) {
        backTrack(board, 0, 0);
        removeCells(sud_problem);
    }

    public static void generateRandomSudoku() {
        sud_problem = new char[N][N];

        // Initialize the Sudoku grid with empty cells
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sud_problem[i][j] = '.';
            }
        }

        // Fill the Sudoku grid with random numbers
        fillRandomNumbers(sud_problem);
    }

    public static void removeCells(char[][] puzzle) {
        int cellsToRemove = random.nextInt(21) + 40;

        while (cellsToRemove > 0) {
            int row = random.nextInt(N);
            int col = random.nextInt(N);

            if (puzzle[row][col] != '.') {
                puzzle[row][col] = '.';
                cellsToRemove--;
            }
        }
    }

    public static boolean isValidNumb(char[][] board, int row, int col, char num) {
        for (int j = 0; j < N; j++) {
            if (board[row][j] == num) {
                return false;
            }
        }

        for (int k = 0; k < N; k++) {
            if (board[k][col] == num) {
                return false;
            }
        }

        int rowStart = (row / 3) * 3;
        int colStart = (col / 3) * 3;

        for (int p = 0; p < 3; p++) {
            for (int q = 0; q < 3; q++) {
                if (board[rowStart + p][colStart + q] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean backTrack(char[][] board, int row, int col) {
        if (row == N - 1 && col == N) {
            return true;
        }

        if (col > 8) {
            col = 0;
            row++;
        }

        if (board[row][col] != '.') {
            return backTrack(board, row, col + 1);
        }

        // Create a random permutation of numbers from '1' to '9'
        char[] nums = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        shuffleArray(nums);

        for (char num : nums) {
            if (isValidNumb(board, row, col, num)) {
                board[row][col] = num;

                if (backTrack(board, row, col + 1)) {
                    return true;
                }
            }
        }

        board[row][col] = '.';

        return false;
    }

    public static void shuffleArray(char[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    public static void solveSudoku(char[][] board) {
        backTrack(board, 0, 0);
    }

    public static String RandomizeButton() {

        // Generiert ein zufälliges Sudoku
        generateRandomSudoku();
        // Gibt das zufällig erstellte und ungelöste Sudoku aus
        System.out.println("Ein zufälliges Sudoku wurde generiert");

        System.out.println();

        return printUI();

    }

    public static void main(String[] args) {
        // Startet die GUI und pausiert den rest des Codes
        launch();
        // Generiert ein zufälliges Sudoku
        generateRandomSudoku();
        // Gibt das zufällig erstellte und ungelöste Sudoku aus
        printSolution();
        System.out.println("Ein zufälliges Sudoku wurde generiert");

        System.out.println();

        // Löst das Sudoku
        solveSudoku(sud_problem);

        // Gibt das gelöste Sudoku aus
        printSolution();
        System.out.println("Das Sudoku wurde gelöst");
    }

    public static void printSolution() {
        for (char[] solution : sud_problem) {
            for (char c : solution) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
    public static String printUI() {
        String sZeile = "";
        for (char[] solution : sud_problem) {
            for (char c : solution) {
                sZeile += c + " ";
            }
            sZeile += "\n";
        }
        return sZeile;
    }
}