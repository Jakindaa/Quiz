package me.arif.quiz.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import me.arif.quiz.manager.QuizManager;
import me.arif.quiz.object.Question;

public class QuizPage extends Controller{
    @FXML
    private Label questionNo;
    @FXML
    private Label question;
    @FXML
    private Button option1;
    @FXML
    private Button option2;
    @FXML
    private Button option3;
    @FXML
    private Button option4;
    @FXML
    private ProgressBar timeBar;
    @FXML
    private Label timeLabel;

    private QuizManager quizManager;

    @Override
    public void init() {
        quizManager = QuizManager.getInstance();
        prepareQuestion();

        timeBar.progressProperty().bind(quizManager.getProgressProperty());
        timeLabel.textProperty().bind(quizManager.getRemainingTimeProperty().asString());
    }

    private void prepareQuestion() {
        Question questionObj = quizManager.getCurrentQuestion();

        questionNo.setText("Question " + (quizManager.getCorrectAnswers() + 1) +  "/" + quizManager.getTotalQuestions());
        question.setText(questionObj.getQuestion());
        option1.setText(questionObj.getOptions().get(0));
        option2.setText(questionObj.getOptions().get(1));
        option3.setText(questionObj.getOptions().get(2));
        option4.setText(questionObj.getOptions().get(3));
    }

    @FXML
    private void chooseAnswer(Event event) {
        Button button = (Button) event.getSource();
        if(quizManager.checkAnswer(button.getText())) {
            prepareQuestion();
        }
    }
}
