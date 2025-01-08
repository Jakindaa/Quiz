package me.arif.quiz.object;


public class History {
    /*
    Class to store information about a quiz.
     */

    private final String date; //HH:MM DD/MM/YYYY
    private final String category;
    private final int correctAnswers;
    private final int numOfQuestions;
    private final int timeLimit;

    public History(String date, String category, int correctAnswers, int numQuestions, int timeLimit) {
        this.date = date;
        this.category = category;
        this.correctAnswers = correctAnswers;
        this.numOfQuestions = numQuestions;
        this.timeLimit = timeLimit;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getNumOfQuestions() {
        return numOfQuestions;
    }

    public int getTimeLimit() {
        return timeLimit;
    }
}
