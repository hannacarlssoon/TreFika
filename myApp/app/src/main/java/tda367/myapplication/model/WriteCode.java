package tda367.myapplication.model;


/**
 * @author Sara Kitzing
 * This class handles the logic behind the write code query
 */

public class WriteCode implements Query {
    private final String question;
    private final String answer;


    public WriteCode (String question, String answer){
        this.question = question;
        this.answer= answer;
    }

    @Override
    public String getQuestion() {
        return question;
    }

    //checks if users answer is correct
    public boolean checkAnswer(String userAnswer){
        return userAnswer.toLowerCase().equals(answer.toLowerCase());

    }

    @Override
    public String getAnswer() {
        return answer;
    }


}
