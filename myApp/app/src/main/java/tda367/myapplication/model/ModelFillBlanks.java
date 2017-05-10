package tda367.myapplication.model;

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

    public ModelFillBlanks (String question, String answer){
        this.question = question;
        this.answer1= answer;
        this.answer2= answer;
        this.answer3= answer;
    }

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
       String[] answers = userAnswer.split(",");
        this.userAnswer1 = answers[0];
        this.userAnswer2 = answers[1];
        this.userAnswer3 = answers[2];
        return userAnswer1.equals(answer1) && userAnswer2.equals(answer2) && userAnswer3.equals(answer3);
    }


}
