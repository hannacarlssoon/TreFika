package tda367.myapplication.model;

/** Created by: Tobias Lindgren, revised by Madeleine and Tobias
 * Responsibilty: Keeping track of the questions, and maintaining them. It also creates the
 * different query objects, each level model object has a query object as an instance variable.
 * Used by: HashMapCreator and LearnJava
 * Uses: Query
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
            this.query = new ModelWriteCode(question, answer);
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
