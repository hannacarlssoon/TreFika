package tda367.myapplication.Model;

/**
 * Created by Sara on 2017-04-07.
 */

public class ModelFillBlanks extends Query  {
    private final String question;
    private final String answer1;
    private final String answer2;
    private final String answer3;
    private String userAnswer1;
    private String userAnswer2;
    private String userAnswer3;


    public ModelFillBlanks (String question, String answer1, String answer2, String answer3){
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
    }


    @Override
    public boolean checkAnswer() {
        return userAnswer1.equals(answer1) && userAnswer2.equals(answer2) && userAnswer3.equals(answer3);
    }

    @Override
    public void input() {


    }
}
