package tda367.myapplication.controller;

import android.content.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tda367.myapplication.model.ModelFillBlanks;
import tda367.myapplication.model.ModelWriteCode;
import tda367.myapplication.model.MultiChoice;
import tda367.myapplication.model.Query;
import tda367.myapplication.service.FileReader;

/**
 * @author Madeleine Lexén and Tobias Lindgren
 * Creates a hashmap of level model objects.
 * Uses Query, FileReader
 * Used by playFragment
 */

public class HashMapCreator {
    private Map<String, Query[]> levelHashMap;
    private FileReader fileReader =  new FileReader();
    private Context context;


    public HashMapCreator(Context context){
        this.context = context;
        init();
    }

    public Map<String, Query[]> getHashMap(){
        return this.levelHashMap;
    }


    private void createCatArrays(){
        for (int i = 1; i < 5; i++) {
            Query[] temp = new Query[5];
            for (int j = 1; j < 6; j++) {
                String fileName = "category" + i +"/" + "level" + j + ".txt";
                Query query;
                List<String> list = fileReader.getTextArray(fileName, context);
                if ( j > 4){
                    query = new ModelWriteCode(list);
                }
                else if(j % 2 == 1){
                    query = new ModelFillBlanks(list);
                }
                else {
                    query = new MultiChoice(list);
                }
                temp[j-1] = query;
            }
            levelHashMap.put("category" + i, temp);
        }

    }

    private void init(){
        levelHashMap = new HashMap<>();
        createCatArrays();
    }

}
