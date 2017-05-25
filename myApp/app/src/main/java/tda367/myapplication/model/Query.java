package tda367.myapplication.model;

import java.util.List;

/**
 * Created by Sara on 2017-04-06.
 * @author Sara Kitzing, revised by Madeleine Lexén
 * This class is an interface to multichoice, write code and fill in the blanks
 */

public abstract class Query {

    public String question;
    public String answer;
    public String hint;
    public String info;
    public String heading;

    public Query(List<String> e){
        question = e.get(0);
        answer = e.get(1).toLowerCase();
        hint = e.get(3);
        info = e.get(2);
        heading = e.get(4);
    }

    public String getQuestion(){
        return question;
    }

    public abstract boolean checkAnswer(String userAnswer);

    public String getAnswer(){
        return answer;
    }

    public String getHint(){
        return hint;
    }

    public String getInfo(){
        return info;
    }

    public String getHeading(){
        return heading;
    }

}
