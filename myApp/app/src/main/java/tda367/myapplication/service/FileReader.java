package tda367.myapplication.service;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FileReader {

    private int counter;
    String stringWRows;

    // Method for getting the required string. Returns the question, the answer or the info of the level
    public String getRequiredText(String fileName, String type, Context context){
        String string = "";
        counter = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName)));
            String lineOfContent = bufferedReader.readLine();
            while (lineOfContent != null){
                counter++;
                if (checkIfQuestion(type)){
                    string = getStringWRows(lineOfContent);
                }
                else if (checkIfAnswer(type)) {
                    string = getStringWRows(lineOfContent);
                }
                else if (checkIfInfo(type)) {
                    string = getStringWRows(lineOfContent);
                }
                else if (checkIfHint(type)) {
                    string = getStringWRows(lineOfContent);
                }
                else if (checkIfAlternatives(type)) {
                    string = getStringWRows(lineOfContent);
                }
                else if (checkIfHeading(type)) {
                    string = getStringWRows(lineOfContent);
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

    //Method for checking if the wanted text is a hint and the line of the .txt-file is hint
    private boolean checkIfHint(String reqText){
        return (reqText.equals("hint") && counter == 8);
    }

    //Method for checking if the wanted text is alternatives and the line of the .txt-file is alternatives
    private boolean checkIfAlternatives(String reqText){
        return (reqText.equals("alternative") && counter == 10);
    }

    //Method for checking if the wanted text is a heading and the line of the .txt-file is heading
    private boolean checkIfHeading(String reqText){
        return (reqText.equals("heading") && counter == 12);
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
        System.out.println(splitString.length + "");
        System.out.println(stringWRows);
    }
}
