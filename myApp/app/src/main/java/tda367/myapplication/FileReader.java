package tda367.myapplication;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import tda367.myapplication.Model.LevelModel;

public class FileReader {
    private Context context;
    private int counter;

    // Method for getting the required string. Returns the question, the answer or the info of the level
    public String getRequiredText(String fileName, String type ){
        String string = "";
        counter = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName)));
            String lineOfContent = bufferedReader.readLine();
            while (lineOfContent != null){
                counter++;
                if (checkIfQuestion(type)){
                    string = lineOfContent;
                }
                else if (checkIfAnswer(type)) {
                    string = lineOfContent;
                }
                else if (checkIfInfo(type)) {
                    string = lineOfContent;
                }
                lineOfContent = bufferedReader.readLine();
            }
        } catch (Exception e){
            System.out.println("Problem with file!");
            System.out.println(e.getMessage());
        }
        return string;
    }


    // Method for checking if the wanted text is a question and the line of the .txt-file is a question
    private boolean checkIfQuestion(String reqText){
        return (reqText.equals("question") && counter == 2);
    }

    // Method for checking if the wanted text is an answer and the line of the .txt-file is an answer
    private boolean checkIfAnswer(String reqText){
        return (reqText.equals("answer") && counter == 4);
    }

    // Method for checking if the wanted text is info and the line of the .txt-file is info
    private boolean checkIfInfo(String reqText){
        return (reqText.equals("info") && counter == 6);
    }
}
