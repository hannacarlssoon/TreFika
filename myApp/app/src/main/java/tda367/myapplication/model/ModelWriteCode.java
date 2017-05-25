package tda367.myapplication.model;


import java.util.List;

/**
 * @author Sara Kitzing
 * This class handles the logic behind the write code query
 * Used by Query
 */

public class ModelWriteCode extends Query {
    private String code;

    public ModelWriteCode(List<String> e){
        super(e);
       code = e.get(5);
    }

    //Checks if users answer is correct
    @Override
    public boolean checkAnswer(String userAnswer){
        return userAnswer.equalsIgnoreCase(answer);

    }
}
