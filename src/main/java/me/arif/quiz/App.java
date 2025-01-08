package me.arif.quiz;

import javafx.application.Application;
import javafx.stage.Stage;
import me.arif.quiz.manager.QuestionsManager;
import me.arif.quiz.manager.QuizHistoryManager;
import me.arif.quiz.manager.SceneManager;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) {

        QuestionsManager.initialize();
        QuizHistoryManager.initialize();
        try {
            SceneManager.initialize(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}