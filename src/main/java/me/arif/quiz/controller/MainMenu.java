package me.arif.quiz.controller;

import javafx.fxml.FXML;
import me.arif.quiz.manager.SceneManager;

public class MainMenu extends Controller{

    @FXML
    private void startQuiz() {
        SceneManager.switchTo("quiz_settings");
    }

    @FXML
    private void quizHistory() {
        SceneManager.switchTo("quiz_history");
    }
}
