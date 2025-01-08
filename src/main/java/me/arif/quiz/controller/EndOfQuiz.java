package me.arif.quiz.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import me.arif.quiz.manager.QuizManager;

public class EndOfQuiz extends Controller {
    @FXML
    private Label infoText1;
    @FXML
    private Label infoText2;
    @FXML
    private Label infoText3;

    @Override
    public void init() {
        QuizManager quizManager = QuizManager.getInstance();
        String quizEndReason = quizManager.getQuizEndReason();
        switch (quizEndReason) {
            case "Answered All" -> {
                infoText1.setText("Congratulations!.");
                infoText2.setText("You have answered all " + quizManager.getCorrectAnswers() + " questions correctly.");
                infoText3.setText("");
            }
            case "Time's Up!", "Wrong Answer!" -> {
                infoText1.setText(quizEndReason);
                infoText2.setText("Correct answer was: " + quizManager.getCurrentQuestion().getAnswer());
                infoText3.setText("You have answered " + quizManager.getCorrectAnswers() + " out of " + quizManager.getTotalQuestions() + " questions correctly.");
            }
        }
    }


}
