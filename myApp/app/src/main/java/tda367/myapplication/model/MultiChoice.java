package tda367.myapplication.model;

/**
 * Created by madeleine on 2017-04-07.
 * This class handles the logic for the multichoice question, checking the answer, uses query
 */

public class MultiChoice implements Query {
    private final String answer;
    private final String question;
    private String[] alt;

    public MultiChoice(String question, String answer, String alternatives){
        this.question = question;
        this.answer = answer.toLowerCase();
        this.alt = alternatives.split(",");
    }


    @Override
    public String getQuestion() {
        return question;
    }


    //Checks if the answer the user has selected is correct.
    @Override
    public boolean checkAnswer(String userAnswer) {
        return answer.equals(userAnswer.toLowerCase());
    }

    @Override
    public String getAnswer() {
        return answer;
    }

    public String getAlt(int altNr){
        return alt[altNr];
    }
}
