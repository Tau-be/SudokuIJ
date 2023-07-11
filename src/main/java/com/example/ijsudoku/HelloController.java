package com.example.ijsudoku;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class HelloController {

    private static char[][] sud_problem;
    @FXML
    private GridPane SudokuGridPane;

    @FXML
    private CheckBox UserInputCheckBox;

    @FXML
    protected void onHelloButtonClick() {
        System.out.println("Hello!");
    }

    @FXML
    protected void onSolveButtonClick() {
        HelloApplication.DoSolve();
    }

    @FXML
    protected void onClearButtonClick() {
        System.out.println("Clear!");
    }

    @FXML
    protected void onRandomizeButtonClick() {
        System.out.println("Randomize!");
        sud_problem = HelloApplication.RandomizeButton();

        // method to fill SudokuGridPane with sud_problem
        for (int i = 0; i < sud_problem.length; i++) {
            for (int j = 0; j < sud_problem[i].length; j++) {
                System.out.println(sud_problem[i][j]);
                Label label = new Label();
                label.setText(String.valueOf(sud_problem[i][j]));
                SudokuGridPane.add(label, j, i);
            }
        }
    }

    @FXML
    protected void onCheckButtonClick() {
        System.out.println("Check!");

    }

    @FXML
    protected void onGridClick() {
        System.out.println("Grid!");
    }
}

