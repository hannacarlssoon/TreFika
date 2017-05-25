package tda367.myapplication.service;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by: Tobias
 * Responsibility: Reading from .txt files containing all level texts
 * Used by: HashMapCreator
 * Uses:
 */

public class FileReader {

    private int counter;
    private String stringWRows;
    private String string;
    List<String> strings;

    // Method for getting the required string. Returns the question, the answer or the info of the level
    public String getRequiredText(String fileName, String type, Context context){
        string = "";
        counter = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName)));
            String lineOfContent = bufferedReader.readLine();
            while (lineOfContent != null){
                counter++;
                if (checkIfQuestion(type) || checkIfInfo(type) || checkIfHint(type)){
                    rowCheck(lineOfContent);
                }
                else if (checkIfAnswer(type) || checkIfAlternatives(type) || checkIfHeading(type)){
                    string = lineOfContent;
                }
                lineOfContent = bufferedReader.readLine();
            }
        } catch (Exception e){
        }
        return string;
    }

    public List<String> getTextArray(String filename, Context context){
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));
            strings = new ArrayList<>();
            String lineOfContent = bufferedReader.readLine();
            while (lineOfContent != null){
                strings.add(lineOfContent);
                lineOfContent = bufferedReader.readLine();
            }
        } catch (IOException e){

        }
        return strings;
    }


    // Method for checking if the wanted text is a question and the line of the .txt-file is a question
    private boolean checkIfQuestion(String reqText){
        return (reqText.equals("question") && counter == 1);
    }

    // Method for checking if the wanted text is an answer and the line of the .txt-file is an answer
    private boolean checkIfAnswer(String reqText){
        return (reqText.equals("answer") && counter == 2);
    }

    // Method for checking if the wanted text is info and the line of the .txt-file is info
    private boolean checkIfInfo(String reqText){
        return (reqText.equals("info") && counter == 3);
    }

    //Method for checking if the wanted text is a hint and the line of the .txt-file is hint
    private boolean checkIfHint(String reqText){
        return (reqText.equals("hint") && counter == 4);
    }

    //Method for checking if the wanted text is alternatives and the line of the .txt-file is alternatives
    private boolean checkIfAlternatives(String reqText){
        return (reqText.equals("alternative") && counter == 5);
    }

    //Method for checking if the wanted text is a heading and the line of the .txt-file is heading
    private boolean checkIfHeading(String reqText){
        return (reqText.equals("heading") && counter == 6);
    }

    //Returns The same string but with newlines
    private String getStringWRows(String fileString){
        stringWRows = "";
        setNewRows(fileString);
        return stringWRows;
    }

    //Method for setting the new rows
    private void setNewRows(String fileString){
        String[] splitString = fileString.split("row");
        for (String string:splitString){
            stringWRows = stringWRows + string + "\n";
        }
    }

    //Method for checking if line has "row"
    private void rowCheck (String lineOfContent) {
        if (lineOfContent.contains("row")){
            string = getStringWRows(lineOfContent);
        }
        else {
            string = lineOfContent;
        }
    }
}
