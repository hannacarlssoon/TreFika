package tda367.myapplication.Model;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class LevelModel {
    private Query query;
    private String levelAnswer = "Answer";
    private String levelQuestion = "Question";
    private String levelInfo = "";
    private String fileLine;

    public LevelModel(String levelNr, String category, boolean boss){
        //Code to get question and answer from file
        if (!boss){
            query = new MultiChoice(levelQuestion, levelAnswer);
            levelInfo = getLevelQuestion();
        }
        //Code for boss question

    }

    private String getLevelQuestion(){
        String information = "";
        try
        {
            BufferedReader fw = new BufferedReader(new FileReader(new File("res/raw/test.txt")));

            while(!((fileLine = fw.readLine()).equals("[Part2]")))
            {
                information = information + fileLine;
                Log.d("TAG", fileLine + "");
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        Log.d("TEST", information + "");
        return information;
    }

    public String getLevelInfo(){
        return this.levelInfo;
    }
}
