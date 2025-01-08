package me.arif.quiz.manager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.arif.quiz.controller.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    /*
    Manager class to initialize scenes and switch between them.
     */

    private static Stage primaryStage;
    private static final Map<String, Scene> scenes = new HashMap<>();
    private static final Map<String, Controller> controllers = new HashMap<>();

    public static void initialize(Stage stage) throws IOException {
        primaryStage = stage;
        loadScene("end_of_quiz", "/me/arif/quiz/end_of_quiz.fxml");
        loadScene("main_menu", "/me/arif/quiz/main_menu.fxml");
        loadScene("quiz_history", "/me/arif/quiz/quiz_history.fxml");
        loadScene("quiz_page", "/me/arif/quiz/quiz_page.fxml");
        loadScene("quiz_settings", "/me/arif/quiz/quiz_settings.fxml");
        switchTo("main_menu");
    }

    public static void loadScene(String name, String fxmlPath) throws IOException {

        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlPath));
        Parent root = loader.load();
        Controller controller = loader.getController();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(SceneManager.class.getResource("/me/arif/quiz/style.css").toExternalForm());
        scenes.put(name, scene);
        controllers.put(name, controller);
    }

    public static void switchTo(String name) {
        Scene scene = scenes.get(name);
        double width = primaryStage.getWidth();
        double height = primaryStage.getHeight();

        primaryStage.setScene(scene);
        primaryStage.setHeight(height);
        primaryStage.setWidth(width);
        primaryStage.show();
        controllers.get(name).init();
    }
}