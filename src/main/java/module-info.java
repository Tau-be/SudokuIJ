module com.example.ijsudoku {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ijsudoku to javafx.fxml;
    exports com.example.ijsudoku;
}