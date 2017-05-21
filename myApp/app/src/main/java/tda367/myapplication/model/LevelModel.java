package tda367.myapplication.model;

/* @author Tobias Lindgren, revised by Madeleine and Tobias
This class is responsible for keeping track of the questions, and maintaining them. It also creates the
different query objects, each level model object has a query object as an instance variable.
 */

public class LevelModel {
    private String info;
    private String hint;
    private Query query;
    private int questionNumber;
    private String heading;


    public LevelModel(String question, String answer, String info, String hint, int questionNumber, String heading, String alternatives) {
        this.info = info;
        this.hint = hint;
        this.heading = heading;
        this.questionNumber = questionNumber;
        if (questionNumber > 4){
            this.query = new WriteCode(question, answer);
        }
        else if ( questionNumber % 2 == 0){
            this.query = new MultiChoice(question, answer,alternatives);
        }
        else if(questionNumber % 2 == 1){
            this.query = new ModelFillBlanks(question, answer);
        }
    }

    public int getQuestionNumber() {
        return this.questionNumber; }

    public Query getQuery(){
        return this.query;
    }

    public String getHint() {
        return hint;
    }

    public String getInfo(){
        return this.info;
    }

    public String getHeading(){
        return this.heading;
    }

    public boolean checkAnswer(String userAnswer){
        //anropa frågans checkAnswer metod
        return query.checkAnswer(userAnswer);
    }
}
