package tda367.myapplication.model;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by: Madeleine Lexen on 2017-04-07. Built by Tobias Lindgren and Madeleine Lexen
 * Responsibility: Maintaining and storing the hashmap containing all the level objects.
 * Used by: ActivityInfo, FillInTheBlanks, LevelActivity, QuestionMultiChoice, WriteCode, PassedLevel, CustomAdapter
 * PlayFragment
 * Uses: HashMapCreator, Query
 */

public class LevelModel {
    private int amountOfCategories = 4;
    private int currentCategory;
    private int currentLevel;
    private Map<String, Query[]> levelHashMap;
    private static LevelModel instance;
    private boolean hasInit = false;


    //initiates the levelModel instance
    public void init(Map<String, Query[]> levelHashMap){
        if(!hasInit){
            this.levelHashMap = levelHashMap;
            hasInit = true;
            currentCategory = 1;
        }
    }

    public Map<String, Query[]> getLevelMap(){
        return levelHashMap;
    }

    public int getCurrentCategory(){
        return currentCategory;
    }

    public int getCurrentLevel(){
        return currentLevel;
    }

    public int getAmountOfCategories(){return amountOfCategories;}

    public static LevelModel getInstance(){
        if(instance == null){
            instance = new LevelModel();
        }
        return instance;
    }

    public Query getQuery(){
        return levelHashMap.get("category" + currentCategory)[currentLevel];
    }

    public void setCurrentCategory(int s){
        currentCategory = s;
    }

    public void setCurrentLevel(int i){
        currentLevel = i;
    }

    public boolean getHasInit(){
        return hasInit;
    }

}
