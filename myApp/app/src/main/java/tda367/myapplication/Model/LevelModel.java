package tda367.myapplication.Model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import tda367.myapplication.*;

public class LevelModel {
    private final Query query;
    private final String questionAnswer = "Answer";
    private final String questionText = "Question";

    public LevelModel(String levelNr, String category, boolean boss){
        //Code to get question and answer from file
        if (!boss){
            query = new MultiChoice(questionText, questionAnswer);
        }
        //Code for boss question

    }
}
