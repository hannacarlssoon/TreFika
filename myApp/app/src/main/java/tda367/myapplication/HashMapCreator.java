package tda367.myapplication;

import android.content.Context;

import java.util.HashMap;

import tda367.myapplication.model.LevelModel;
import tda367.myapplication.service.FileReader;

/**
 * Created by madeleine on 2017-05-10.
 * Creates a hashmap of level model objects.
 */

public class HashMapCreator {
    private HashMap<String, LevelModel[]> levelHashMap;
    private LevelModel[] category1= new LevelModel[5];
    private LevelModel[] category2 = new LevelModel[5];
    private LevelModel[] category3 = new LevelModel[5];
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
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 6; j++) {
                String fileName = "category" + i +"/" + "level" + j + ".txt";
                LevelModel levelModel = new LevelModel(fileReader.getRequiredText(fileName, "question", context), fileReader.getRequiredText(fileName, "answer", context), fileReader.getRequiredText(fileName, "info", context), fileReader.getRequiredText(fileName, "hint", context), j);
                if(i == 1){
                    category1[j-1] = levelModel;
                }
                else if(i == 2){
                    category2[j-1] = levelModel;
                }
                else if(i == 3) {
                    category3[j-1] = levelModel;
                }

            }
        }

    }

    private void init(){
        levelHashMap = new HashMap<>();
        createCatArrays();
        fillHashMap();
    }

    private void fillHashMap(){
        levelHashMap.put("category1", category1);
        levelHashMap.put("category2", category2);
        levelHashMap.put("category3", category3);
    }
}
