package davinci.library;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import davinci.model.User;
import davinci.model.DataWriter;
import davinci.model.Faculty;
import davinci.model.Major;
import davinci.model.MajorList;
import davinci.model.Student;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static User user = null;
    public static Student student = null;
    public static Faculty faculty = null;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 800, 450);
        stage.setScene(scene);

        stage.setMinHeight(450);
        stage.setMinWidth(800);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}