package me.arif.quiz.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import me.arif.quiz.manager.QuizHistoryManager;
import me.arif.quiz.object.History;

import java.util.List;

public class QuizHistory extends Controller{
    @FXML
    private TableView<History> history;
    @FXML
    private TableColumn<History, String> date;
    @FXML
    private TableColumn<History, String> category;
    @FXML
    private TableColumn<History, Integer> correctAnswers;
    @FXML
    private TableColumn<History, Integer> numOfQuestions;
    @FXML
    private TableColumn<History, Integer> timeLimit;

    @Override
    public void init() {
        List<History> quizHistory = QuizHistoryManager.getQuizHistory();
        ObservableList<History> historyList = FXCollections.observableArrayList(quizHistory);

        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        correctAnswers.setCellValueFactory(new PropertyValueFactory<>("correctAnswers"));
        numOfQuestions.setCellValueFactory(new PropertyValueFactory<>("numOfQuestions"));
        timeLimit.setCellValueFactory(new PropertyValueFactory<>("timeLimit"));

        history.setItems(historyList);
    }
}