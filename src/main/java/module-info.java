module davinci {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens davinci to javafx.fxml;
    exports davinci;
}
