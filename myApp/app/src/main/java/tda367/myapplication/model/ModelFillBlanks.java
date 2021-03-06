package tda367.myapplication.model;

import java.util.List;

/**
 * @author Sara Kitzing
 * Responsible for the logic behind the fill in the blanks query
 * Uses: Query
 * Used by: FillInTheBlanks, HashMapCreator
 */


public class ModelFillBlanks extends Query  {

    public ModelFillBlanks(List<String> e){
        super(e);
    }

    //Checks if the users answer is corret
    @Override
    public boolean checkAnswer(String userAnswer) {
    return userAnswer.equalsIgnoreCase(answer);
    }



}
