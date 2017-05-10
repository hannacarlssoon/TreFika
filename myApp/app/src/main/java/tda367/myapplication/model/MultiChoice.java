package tda367.myapplication.model;

/**
 * Created by madeleine on 2017-04-07.
 */

public class MultiChoice extends Query {
    private final String answer;
    private final String question;

    public MultiChoice(String question, String answer){
        this.question = question;
        this.answer = answer;
    }

    @Override
    public String getQuestion() {
        return question;
    }


    //Checks if the answer the user has selected is correct.
    @Override
    public boolean checkAnswer(String userAnswer) {
        return answer.equals(userAnswer);
    }


   /* @Override
    public void getInput(String answer) {

    }*/
}
