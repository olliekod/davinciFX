module davinci {
    requires javafx.controls;
    requires javafx.fxml;

    opens davinci to javafx.fxml;
    exports davinci;
}
