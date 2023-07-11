package com.example.ijsudoku;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.util.*;

public class HelloController {

    private static char[][] sud_problem;
    private Label zahl;

    @FXML
    private Label TerminalLabel;
    @FXML
    private GridPane SudokuGridPane;

    @FXML
    private CheckBox UserInputCheckBox;

    @FXML
    protected void onSolveButtonClick() {
        TerminalLabel.setText(HelloApplication.DoSolve());
    }

    @FXML
    protected void onClearButtonClick() {
        System.out.println("Clear!");
        TerminalLabel.setText("The Peak of UI is BACK!");
    }

    @FXML
    protected void onRandomizeButtonClick() {
        System.out.println("Randomize!");
        TerminalLabel.setText(HelloApplication.RandomizeButton());

        // Hinzufuegbar sind noch Lineien damit die "Boxen" jeweils 3x3 sind und somit lesbarer werden
    }

    @FXML
    protected void onCheckButtonClick() {
        System.out.println("Check!");

    }

    @FXML
    protected void onGridClick() {

        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        zahl.setText(String.valueOf(number));
        System.out.println(String.valueOf(number));
    }
}

