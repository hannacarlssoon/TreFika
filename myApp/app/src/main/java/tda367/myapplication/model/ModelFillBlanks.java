package tda367.myapplication.model;

/**
 * @author Sara Kitzing
 * This class handles the logic behind the fill in the blanks query
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

    //checks if the users answer is corret
    @Override
    public boolean checkAnswer(String userAnswer) {
    return userAnswer.toLowerCase().equals(answer);
    }

    @Override
    public String getAnswer() {
        return answer;
    }


}
