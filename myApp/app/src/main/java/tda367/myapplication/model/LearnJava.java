package tda367.myapplication.model;

import java.util.HashMap;


/**
 * Created by madeleine on 2017-04-07. Tobias och Madeleine har byggt den här klassen gemmensamt.
 * This class is responsible for maintaining and storing the hashmap containing all the level objects.
 * Uses HashMapCreator
 */

public class LearnJava {
    private int amountOfCategories = 4;
    private int currentCategory;
    private int currentLevel;
    private HashMap<String, LevelModel[]> levelHashMap;
    private static LearnJava instance;
    private boolean hasInit = false;

    public void init(HashMap<String, LevelModel[]> levelHashMap){
        if(!hasInit){
            this.levelHashMap = levelHashMap;
            hasInit = true;
            currentCategory = 1;
        }
    }

    public HashMap<String, LevelModel[]> getLevelHashMap(){
        return levelHashMap;
    }

    public int getCurrentCategory(){
        return currentCategory;
    }

    public int getCurrentLevel(){
        return currentLevel;
    }

    public int getAmountOfCategories(){return amountOfCategories;}

    public static LearnJava getInstance(){
        if(instance == null){
            instance = new LearnJava();
        }
        return instance;
    }

    public LevelModel getLevelModel(){
        return levelHashMap.get("category" + currentCategory)[currentLevel];
    }

    public void setCurrentCategory(int s){
        currentCategory = s;
    }

    public void setCurrentLevel(int i){
        currentLevel = i;
    }

}
