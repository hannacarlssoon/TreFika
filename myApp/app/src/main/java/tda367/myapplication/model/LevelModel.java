package tda367.myapplication.model;

/* Den här klassen skapades av tobias, och har sen byggts vidare på av både Madeleine och Tobias.
This class is responsible for keeping track of the questions, and maintaining them. It also creates the
different query objects, each level model object has a query object as an instance variable.
 */

public class LevelModel {
    private int number;
    private String question;
    private String answer;
    private String info;
    private String hint;
    private Query query;
    private int questionNumber;

    public LevelModel(String question, String answer, String info, String hint, int questionNumber) {
        this.question = question;
        this.answer = answer;
        this.info = info;
        this.hint = hint;
        this.questionNumber = questionNumber;
        if (questionNumber > 4){
            this.query = new WriteCode(question, answer);
        }
        else if ( questionNumber % 2 == 0){
            this.query = new MultiChoice(question, answer);
        }
        else if(questionNumber % 2 == 1){
            this.query = new ModelFillBlanks(question, answer);
        }
    }

    public int getNumber(){
        return this.number;
    }

    public String getQuestion(){
        return this.question;
    }

    public int getQuestionNumber(){return this.questionNumber; }

    public String getAnswer(){
        return this.answer;
    }

    public Query getQuery(){
        return this.query;
    }

    public String getHint() {
        return hint;
    }

    public String getInfo(){
        return this.info;
    }

    public boolean checkAnswer(String userAnswer){
        //anropa frågans checkAnswer metod
        return query.checkAnswer(userAnswer);
    }
}
