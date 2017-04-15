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
    private boolean part2 = false;

    public LevelModel(String levelNr, String category, boolean boss){
        //Code to get question and answer from file
        if (!boss){
            query = new MultiChoice(getLevelQuestion(), levelAnswer);
            levelInfo = getLevelQuestion();
        }
        //Code for boss question

    }

    private String getLevelQuestion(){
        String s = "";
        try {
            BufferedReader fw = new BufferedReader(new FileReader(new File("test.txt")));

            fileLine = fw.readLine();
            while(fileLine != null) {
                switch (fileLine){
                    case "[Question1]": part2 = true;
                        break;
                    case "[Answer1]": part2 = false;
                        break;
                    case "[Info1]": part2 = false;
                }
                if (part2) {
                    s = s + fileLine.replaceAll("\\[.*?\\]", "");
                }
                fileLine = fw.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return s;
    }

    public String getLevelInfo(){
        return this.levelInfo;
    }
}
