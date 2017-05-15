package tda367.myapplication.model;

/**
 * Created by Sara on 2017-04-06.
 * @author Sara Kitzing, revised by Madeleine Lexén
 * This class is a superclass to multichoice, write code and fill in the blanks
 */

public interface Query {

    public String getQuestion();

    public boolean checkAnswer(String userAnswer);

    public String getAnswer();

    //public abstract void getInput(String answer);

}
