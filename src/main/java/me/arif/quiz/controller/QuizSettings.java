package me.arif.quiz.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import me.arif.quiz.manager.QuestionsManager;
import me.arif.quiz.manager.QuizManager;
import me.arif.quiz.manager.SceneManager;
import me.arif.quiz.object.Question;

import java.util.List;

public class QuizSettings extends Controller{
    @FXML
    private ComboBox<String> category;
    @FXML
    private ComboBox<Integer> numOfQuestions;
    @FXML
    private ComboBox<Integer> timeLimit;

    @FXML
    private void start() {
        String category = this.category.getValue().toLowerCase();
        int numOfQuestions = this.numOfQuestions.getValue();
        int timeLimit = this.timeLimit.getValue();
        List<Question> questions = QuestionsManager.getQuestions(category).getMerged(numOfQuestions);
        new QuizManager(questions.subList(0, numOfQuestions),category, timeLimit);
        SceneManager.switchTo("quiz_page");
    }
}
