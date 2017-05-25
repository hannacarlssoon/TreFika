package tda367.myapplication.model;

import java.util.List;

/**
 * Created by madeleine on 2017-04-07.
 * @author madeleine
 * This class handles the logic for the multichoice question, checking the answer,
 * uses query
 * Used by Query, ActivityInfo, QuestionMultiChoice
 */

public class MultiChoice extends Query {
    private String[] alt;

    public MultiChoice(List<String> e){
        super(e);
        alt = e.get(5).split("#");
    }

    //Checks if the answer the user has selected is correct.
    @Override
    public boolean checkAnswer(String userAnswer) {
        return answer.equals(userAnswer.toLowerCase());
    }

    public String getAlt(int altNr){
        return alt[altNr];
    }
}
