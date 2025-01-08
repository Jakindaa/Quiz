package me.arif.quiz.object;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class QuestionSet {
    /*
    Class for storing question lists by weight.
    This is my solution for sorting the questions from easy to difficult.
    There is one QuestionSet object for every category.
     */

    private final HashMap<Integer, List<Question>> questions;

    public QuestionSet() {
        questions = new HashMap<>();
        for (int i = 20; i <= 100; i += 20) {
            questions.put(i, new ArrayList<>());
        }
    }

    public void addQuestion(Question question) {
        questions.get(question.getWeight()).add(question);
    }

    public List<Question> getMerged(int numberOfQuestions) {
        List<Question> merged = new ArrayList<>();
        for (int i = 20; i <= 100; i+=20) {
            Collections.shuffle(questions.get(i));
            merged.addAll(questions.get(i).subList(0, numberOfQuestions / 5));
        }
        return merged;
    }
}
