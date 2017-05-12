package tda367.myapplication.model;

/**
 * @author Sara Kitzing
 * This class handles the logic behind the fill in the blanks query
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
        String[] answers = answer.split(",");
        this.answer1= answers[0];
        this.answer2= answers[1];
        this.answer3= answers[2];
    }

    @Override
    public String getQuestion() {
        return question;
    }

    //checks if the users answer is corret
    @Override
    public boolean checkAnswer(String userAnswer) {
       String[] answers = userAnswer.split(",");
        this.userAnswer1 = answers[0].replaceAll("\\s", "");
        this.userAnswer2 = answers[1].replaceAll("\\s", "");
        this.userAnswer3 = answers[2].replaceAll("\\s", "");
        return userAnswer1.equals(answer1) && userAnswer2.equals(answer2) && userAnswer3.equals(answer3);
    }


}
