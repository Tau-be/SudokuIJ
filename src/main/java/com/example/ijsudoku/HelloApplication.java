package com.example.ijsudoku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static final int N = 9;
    private static char[][] sud_problem;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public static void main(String[] args) {

        // hier wird die UI gelaunched
        launch();

            // hier wird das Board erstellt.

            sud_problem = new char[][]{
                    {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                    {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                    {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                    {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                    {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                    {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                    {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                    {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                    {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
            };

            // Funktion zur Ausführung des Solver's.
            System.out.println(backTrack(sud_problem, 0, 0));

            // Ausgabe in der Console.
            for (char[] solution : sud_problem) {
                for (char c : solution) {
                    System.out.print(c + " ");
                }
                System.out.println();
            }

        }

        // Funktion welche validity von einem Feld prüft.
        public static boolean isValidNumb ( char[][] board, int row, int col, char num){
            // überprüft ob die num in der Spalte bereits vorhanden ist.
            for (int j = 0; j < N; j++) {
                if (board[row][j] == num) {
                    return false;
                }
            }

            // überprüft ob die num in der Zeile bereits vorhanden ist.
            for (int k = 0; k < N; k++) {
                if (board[k][col] == num) {
                    return false;
                }
            }

            // ermittelt das Triplet.
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

        public static boolean backTrack ( char[][] board, int row, int col){
            // problem gelößt
            if (row == N - 1 && col == N) {
                return true;
            }

            //sprung in die nächste zeile
            if (col > 8) {
                col = 0;
                row++;
            }

            // rekursionsschritt für vorausgefüllte Felder.
            if (board[row][col] != '.') {
                return backTrack(board, row, col + 1);
            }
            // Iteriere über alle möglichkeiten
            for (char num = '1'; num <= '9'; num++) {
                // Iteriere über die Möglichkeiten bis der erste wert passt
                if (isValidNumb(board, row, col, num)) {
                    // Überschreibt das Feld mit dem Wert
                    board[row][col] = num;

                    // Überprüft die Lösbarkeit
                    if (backTrack(board, row, col + 1)) {
                        return true;
                    }
                }
            }

            board[row][col] = '.';

            return false;
        }
    }