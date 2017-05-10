package tda367.myapplication.model;

import java.util.HashMap;


/**
 * Created by madeleine on 2017-04-07. Tobias och Madeleine har byggt den här klassen gemmensamt.
 * This class is responsible for maintaining and storing the hashmap containing all the level objects.
 */

public class LearnJava {
    private String currentCategory;
    private int currentLevel;
    public HashMap<String, LevelModel[]> levelHashMap;
    private static LearnJava instance;
    private boolean hasInit = false;

    public void init(HashMap<String, LevelModel[]> levelHashMap){
        if(!hasInit){
            this.levelHashMap = levelHashMap;
            hasInit = true;
        }
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

    public String getQuestion(){
        return getQuery().getQuestion();
        //hämta frågan från mapen, skapa rätt questionobject alt. skapa alla frågor vid instatnsiering och lägga dem i mapen.
    }

    public LevelModel getLevelModel(){
        return levelHashMap.get(currentCategory)[currentLevel];
    }

    public Query getQuery(){
       return levelHashMap.get(currentCategory)[currentLevel].getQuery();
    }

    public void setCurrentCategory(String s){
        currentCategory = s;
    }

    public void setCurrentLevel(int i){
        currentLevel = i;
    }

}
