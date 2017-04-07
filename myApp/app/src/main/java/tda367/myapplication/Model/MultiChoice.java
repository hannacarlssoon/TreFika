package tda367.myapplication.Model;

/**
 * Created by madeleine on 2017-04-07.
 */

public class MultiChoice extends Query {
    private final String answer;
    private final String question;
    private String userAnswer;

    public MultiChoice(String question, String answer){
        this.question = question;
        this.answer = answer;
    }


    //Checks if the answer the user has selected is correct.
    @Override
    public boolean checkAnswer(String answer) {
        return answer.equals(this.answer);
    }

   /* @Override
    public void getInput(String answer) {

    }*/
}
