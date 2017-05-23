package tda367.myapplication.controller;

import android.content.Context;

import java.util.HashMap;

import tda367.myapplication.model.LevelModel;
import tda367.myapplication.service.FileReader;

/**
 * Created by madeleine and Tobias on 2017-05-10.
 * Creates a hashmap of level model objects. Uses LevelModel, used by playFragment
 */

public class HashMapCreator {
    private HashMap<String, LevelModel[]> levelHashMap;
    private FileReader fileReader =  new FileReader();
    private Context context;


    public HashMapCreator(Context context){
        this.context = context;
        init();
    }

    public HashMap<String, LevelModel[]> getHashMap(){
        return this.levelHashMap;
    }


    private void createCatArrays(){
        for (int i = 1; i < 5; i++) {
            LevelModel[] temp = new LevelModel[5];
            for (int j = 1; j < 6; j++) {
                String fileName = "category" + i +"/" + "level" + j + ".txt";
                LevelModel levelModel = new LevelModel(fileReader.getRequiredText(fileName, "question", context), fileReader.getRequiredText(fileName, "answer", context), fileReader.getRequiredText(fileName, "info", context), fileReader.getRequiredText(fileName, "hint", context), j, fileReader.getRequiredText(fileName, "heading", context), fileReader.getRequiredText(fileName, "alternative", context));
                temp[j-1] = levelModel;
            }
            levelHashMap.put("category" + i, temp);
        }

    }

    private void init(){
        levelHashMap = new HashMap<>();
        createCatArrays();
    }

}
