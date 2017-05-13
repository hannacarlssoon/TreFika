package tda367.myapplication.model;

/**
 * Created by Sara on 2017-04-07.
 */

//Handles the logic behind the fill in the blanks query
public class ModelFillBlanks implements Query  {
    private final String question;
    private final String answer1;
    private final String answer2;
    private final String answer3;
    private String userAnswer1;
    private String userAnswer2;
    private String userAnswer3;


    //TODO fix answer-array
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
        this.userAnswer1 = answers[0].replaceAll("\\s", "");
        this.userAnswer2 = answers[1].replaceAll("\\s", "");
        this.userAnswer3 = answers[2].replaceAll("\\s", "");
        return userAnswer1.equals(answer1) && userAnswer2.equals(answer2) && userAnswer3.equals(answer3);
    }


}
