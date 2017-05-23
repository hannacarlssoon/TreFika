package tda367.myapplication.model;


/**
 * @author Sara Kitzing
 * This class handles the logic behind the write code query
 * Used by LevelModel
 */

public class ModelWriteCode implements Query {
    private final String question;
    private final String answer;


    public ModelWriteCode(String question, String answer){
        this.question = question;
        this.answer= answer;
    }

    @Override
    public String getQuestion() {
        return question;
    }

    //Checks if users answer is correct
    @Override
    public boolean checkAnswer(String userAnswer){
        return userAnswer.equalsIgnoreCase(answer);

    }

    @Override
    public String getAnswer() {
        return answer;
    }


}
