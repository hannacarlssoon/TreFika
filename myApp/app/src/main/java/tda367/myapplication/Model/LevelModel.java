package tda367.myapplication.Model;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class LevelModel {
    private int number;
    private String question;
    private String answer;
    private String info;

    public LevelModel(int number, String question, String answer, String info) {
        this.number = number;
        this.question = question;
        this.answer = answer;
        this.info = info;
    }

    public int getNumber(){
        return this.number;
    }

    public String getQuestion(){
        return this.question;
    }

    public String getAnswer(){
        return this.answer;
    }

    public String getInfo(){
        return this.info;
    }
}
