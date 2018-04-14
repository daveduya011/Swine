package com.isidoreofseville.swine;

/**
 * Created by Dave on 1/28/2018.
 */

public class QuestionSet {

    private String choice1, choice2, choice3, choice4;
    private String question;
    private int correctAnswer;

    public QuestionSet(String question, String choice1, String choice2, String choice3, String choice4, int correctAnswer) {
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.question = question;
        this.correctAnswer = correctAnswer;
    }

    public QuestionSet(String question, String choice1, String choice2, String choice3, int correctAnswer) {
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.question = question;
        this.correctAnswer = correctAnswer;
    }

    public QuestionSet(String question, String choice1, String choice2, int correctAnswer) {
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.question = question;
        this.correctAnswer = correctAnswer;
    }


    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public String getChoice1() {
        return choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswerString(){
        if (correctAnswer == 1){
            return choice1;
        }else if (correctAnswer == 2){
            return choice2;
        }else if (correctAnswer == 3){
            return choice3;
        }else if (correctAnswer == 4){
            return choice4;
        } else {
            return null;
        }

    }
}
