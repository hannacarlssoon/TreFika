package tda367.myapplication.Model;

// Den här klassen skapades av tobias, och har sen byggts vidare på av både Madeleine och Tobias.

public class LevelModel {
    private int number;
    private String question;
    private String answer;
    private String info;
    private String hint;
    private Query query;

    public LevelModel(String question, String answer, String info, String hint, int questionNumber) {
        this.question = question;
        this.answer = answer;
        this.info = info;
        this.hint = hint;
        if (questionNumber > 4){
            //TODO Skapa view för kompilering av kod och model
        }
        else if ( questionNumber % 2 == 0){
            query = new MultiChoice(question, answer);
        }
        else if(questionNumber % 2 == 1){
            query = new ModelFillBlanks(question, answer);
        }
    }

    public int getNumber(){
        return this.number;
    }

    public String getQuestion(){
        return this.question;
    }

    public String getAnswer(){
        return this.answer;
    }

    public String getInfo(){
        return this.info;
    }
}
