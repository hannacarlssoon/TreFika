package tda367.myapplication.model;

/**
 * Created by Sara on 2017-04-06.
 */

public abstract class Query {

    public void getHint(){
    }

    public void getKey(){
    }

    public abstract String getQuestion();

    public abstract boolean checkAnswer(String userAnswer);

    //public abstract void getInput(String answer);




}
