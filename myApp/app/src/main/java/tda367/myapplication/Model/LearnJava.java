package tda367.myapplication.Model;

/**
 * Created by madeleine on 2017-04-07.
 */

public class LearnJava {
    private static LearnJava instance;

    private LearnJava(){
    }

    public static LearnJava getInstance(){
        if(instance == null){
            instance = new LearnJava();
        }
        return instance;
    }

    public void getQuestion(String key){
        //hämta frågan från mapen, skapa rätt questionobject alt. skapa alla frågor vid instatnsiering och lägga dem i mapen.
    }

    public boolean checkAnswer(String userAnswer, String key){
        //getQuestion
        //anropa frågans checkAnswer metod
        return false;
    }
}
