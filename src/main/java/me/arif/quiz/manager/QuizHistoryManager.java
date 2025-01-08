package me.arif.quiz.manager;

import me.arif.quiz.object.History;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizHistoryManager {
    /*
    Database manager to store quiz history.
     */

    private static Connection connection;

    public static void initialize() {
        String DB_URL = "jdbc:sqlite:quiz_history.db";
        try {
            connection = DriverManager.getConnection(DB_URL);
            String sql = "CREATE TABLE IF NOT EXISTS quiz_history (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "date TEXT, " +
                    "category TEXT, " +
                    "correct_answers INTEGER, " +
                    "num_of_questions INTEGER, " +
                    "time_limit INTEGER)";
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addQuizHistory(History quizHistory) {
        String sql = "INSERT INTO quiz_history (date, category, correct_answers, num_of_questions, time_limit) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, quizHistory.getDate());
            stmt.setString(2, quizHistory.getCategory());
            stmt.setInt(3, quizHistory.getCorrectAnswers());
            stmt.setInt(4, quizHistory.getNumOfQuestions());
            stmt.setInt(5, quizHistory.getTimeLimit());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<History> getQuizHistory() {
        List<History> historyList = new ArrayList<>();
        String sql = "SELECT * FROM quiz_history";
        try (Statement stmt = connection.createStatement(); ResultSet resultSet = stmt.executeQuery(sql)) {
            while (resultSet.next()) {
                History history = new History(
                    resultSet.getString("date"),
                    resultSet.getString("category"),
                    resultSet.getInt("correct_answers"),
                    resultSet.getInt("num_of_questions"),
                    resultSet.getInt("time_limit")
                );
                historyList.add(history);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historyList;
    }
}