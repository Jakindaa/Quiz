package me.arif.quiz.object;

import java.util.List;

public class Question {
    /*
    Class to store question.
     */

    private final String question;
    private final List<String> options;
    private final String answer;
    private final int weight;

    public Question(String question, List<String> options, String correctAnswer, int weight) {
        this.question = question;
        this.options = options;
        this.answer = correctAnswer;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return """
                Question(
                    question='%s',
                    options=%s,
                    answer='%s'
                )
                """.formatted(question, options, answer);
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getAnswer() {
        return answer;
    }

    public int getWeight() {
        return weight;
    }
}
