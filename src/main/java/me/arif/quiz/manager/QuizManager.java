package me.arif.quiz.manager;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;
import me.arif.quiz.object.Question;
import me.arif.quiz.object.History;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class QuizManager {
    /*
    Class for management of quiz like questions, time etc.
    It stores its instance as static because we want to access this anywhere.
    Everytime user begins a new quiz, new manager will be instantiated and old
    one will be gone. It is instantiated in QuizSettings class.
     */

    private static QuizManager instance;

    private final Iterator<Question> questions;
    private final int timeLimit;
    private final int totalQuestions;
    private final String category;

    private Question currentQuestion;
    private int correctAnswers;
    private String quizEndReason;

    private long remainingMillis;
    private final IntegerProperty remainingTime = new SimpleIntegerProperty();
    private final DoubleProperty progress = new SimpleDoubleProperty();

    private Timeline timeline;

    public QuizManager(List<Question> questions, String category, int timeLimit) {
        instance = this;
        this.questions = questions.iterator();
        this.category = category;
        this.timeLimit = timeLimit;
        totalQuestions = questions.size();
        currentQuestion = this.questions.next();

        startTimer();
    }

    public boolean checkAnswer(String answer) {
        if (!currentQuestion.getAnswer().equalsIgnoreCase(answer)) {
            endQuiz("Wrong Answer!");
            return false;
        }
        correctAnswers++;
        if (!questions.hasNext()) {
            endQuiz("Answered All");
            return false;
        }
        currentQuestion = questions.next();
        startTimer();
        return true;
    }

    public void startTimer() {
        stopTimer();
        remainingMillis = timeLimit * 1000L;
        remainingTime.set(timeLimit);
        progress.set(1.0);

        timeline = new Timeline(new KeyFrame(Duration.millis(25), event -> {
            remainingMillis -= 25;
            remainingTime.set((int) Math.ceilDiv(remainingMillis, 1000));
            progress.set((double) remainingMillis / (timeLimit * 1000));
            if (remainingMillis <= 0) endQuiz("Time's Up!");
        }));
        timeline.setCycleCount((int) ((remainingMillis + 25) / 25));
        timeline.play();
}

    public void stopTimer() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    public void endQuiz(String quizEndReason) {
        stopTimer();
        this.quizEndReason = quizEndReason;
        History history = new History(
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                category,
                correctAnswers,
                totalQuestions,
                timeLimit
        );
        QuizHistoryManager.addQuizHistory(history);
        SceneManager.switchTo("end_of_quiz");
    }


    public static QuizManager getInstance() {
        return instance;
    }

    public IntegerProperty getRemainingTimeProperty() {
        return remainingTime;
    }

    public DoubleProperty getProgressProperty() {
        return progress;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public String getQuizEndReason() {
        return quizEndReason;
    }
}
