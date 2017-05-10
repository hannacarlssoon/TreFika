package tda367.myapplication.model;

import java.util.HashMap;

import tda367.myapplication.service.FileReader;

/**
 * Created by madeleine on 2017-04-07. Tobias och Madeleine har byggt den här klassen gemensamt.
 *
 */

public class LearnJava {
    private String currentCategory;
    private int currentLevel;
    public HashMap<String, LevelModel[]> levelHashMap;
    private static LearnJava instance;

    public void init(HashMap<String, LevelModel[]> levelHashMap){
        this.levelHashMap = levelHashMap;
    }

    public HashMap<String, LevelModel[]> getLevelHashMap(){
        return levelHashMap;
    }

    public String getCurrentCategory(){
        return currentCategory;
    }

    public int getCurrentLevel(){
        return currentLevel;
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
    public void setCurrentCategory(String s){
        currentCategory = s;
    }

    public void setCurrentLevel(int i){
        currentLevel = i;
    }

}
