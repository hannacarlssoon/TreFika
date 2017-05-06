package tda367.myapplication.Model;

import java.util.HashMap;

import tda367.myapplication.FileReader;

/**
 * Created by madeleine on 2017-04-07. Tobias och Madeleine har byggt den här klassen gemensamt.
 */

public class LearnJava {
    private String currentCategory;
    private int currentLevel;
    public HashMap<String, LevelModel[]> levelHashMap;
    private LevelModel[] category1= new LevelModel[5];
    private LevelModel[] category2 = new LevelModel[5];
    private LevelModel[] category3 = new LevelModel[5];
    private FileReader fileReader =  new FileReader();
    private static LearnJava instance;

    private LearnJava(){
        levelHashMap = new HashMap<>();
        createCatArrays();
        fillHashMap();
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
    private void createCatArrays(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                String fileName = "category" + i +"/" + "level" + j + ".txt";
                LevelModel levelModel = new LevelModel(fileReader.getRequiredText(fileName, "question"), fileReader.getRequiredText(fileName, "answer"), fileReader.getRequiredText(fileName, "info"), fileReader.getRequiredText(fileName, "hint"), i*j);
                if(i == 0){
                    category1[j] = levelModel;
                }
                else if(i == 1){
                    category2[j] = levelModel;
                }
                else if(i == 2) {
                    category3[j] = levelModel;
                }

            }
        }

    }


    private void fillHashMap(){
        levelHashMap.put("category1", category1);
        levelHashMap.put("category2", category2);
        levelHashMap.put("category3", category3);
    }
}
