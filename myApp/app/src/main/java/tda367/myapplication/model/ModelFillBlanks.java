package tda367.myapplication.model;

/**
 * @author Sara Kitzing
 * Responsible for the logic behind the fill in the blanks query
 * Used by LevelModel
 */


public class ModelFillBlanks implements Query  {
    private final String question;
    private final String answer;

    public ModelFillBlanks (String question, String answer){
        this.question = question;
        this.answer = answer.toLowerCase();
    }

    @Override
    public String getQuestion() {
        return question;
    }

    //Checks if the users answer is corret
    @Override
    public boolean checkAnswer(String userAnswer) {
    return userAnswer.equalsIgnoreCase(answer);
    }

    @Override
    public String getAnswer() {
        return answer;
    }


}
