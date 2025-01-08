package me.arif.quiz.manager;

import me.arif.quiz.object.Question;
import me.arif.quiz.object.QuestionSet;

import java.sql.*;
import java.util.HashMap;
import java.util.List;

public class QuestionsManager {
    /*
    Get all questions from database when program starts and store them in a static field to
    be able to access anywhere.
     */

    private static final HashMap<String, QuestionSet>  questions = new HashMap<>();

    public static void initialize() {
        String DB_PATH = "jdbc:sqlite:questions.db";
        try (Connection conn = DriverManager.getConnection(DB_PATH); Statement stmt = conn.createStatement()) {
            for (String category : new String[]{"general", "science", "sport", "art"}) {
                ResultSet result = stmt.executeQuery("SELECT * FROM " + category + "_questions");
                addQuestions(result, category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addQuestions(ResultSet result, String category) throws SQLException {
        QuestionSet questionSet = new QuestionSet();
        while (result.next()) {
            String question = result.getString("question");
            String option1 = result.getString("option1");
            String option2 = result.getString("option2");
            String option3 = result.getString("option3");
            String option4 = result.getString("option4");
            String answer = result.getString("answer");
            int weight = result.getInt("weight");
            Question questionObj = new Question(question, List.of(option1, option2, option3, option4), answer, weight);
            questionSet.addQuestion(questionObj);
        }
        questions.put(category, questionSet);
    }

    public static QuestionSet getQuestions(String category) {
        return questions.get(category);
    }
}
