module davinci {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens davinci.controllers to javafx.fxml;
    exports davinci.controllers;
    opens davinci.library to javafx.fxml;
    exports davinci.library;
    opens davinci.model to javafx.fxml;
    exports davinci.model;
}
