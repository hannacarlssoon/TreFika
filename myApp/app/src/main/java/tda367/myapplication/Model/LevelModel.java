package tda367.myapplication.Model;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class LevelModel {
    private boolean q11 = false;
    private boolean a11 = false;
    private boolean i11 = false;
    private boolean q12 = false;
    private boolean a12 = false;
    private boolean i12 = false;
    private boolean q13 = false;
    private boolean a13 = false;
    private boolean i13 = false;
    private boolean q14 = false;
    private boolean a14 = false;
    private boolean i14 = false;
    private boolean q21 = false;
    private boolean a21 = false;
    private boolean i21 = false;
    private boolean q22 = false;
    private boolean a22 = false;
    private boolean i22 = false;
    private boolean q23 = false;
    private boolean a23 = false;
    private boolean i23 = false;
    private boolean q24 = false;
    private boolean a24 = false;
    private boolean i24 = false;
    private boolean q31 = false;
    private boolean a31 = false;
    private boolean i31 = false;
    private boolean q32 = false;
    private boolean a32 = false;
    private boolean i32 = false;
    private boolean q33 = false;
    private boolean a33 = false;
    private boolean i33 = false;
    private boolean q34 = false;
    private boolean a34 = false;
    private boolean i34 = false;

    private boolean trueIfWantedContent = false;

    private Query query;
    private String levelInfo;

    public LevelModel(String levelNr, boolean boss){
        if (!boss){
            query = new MultiChoice(getText("question" + levelNr), getText("answer" + levelNr));
            levelInfo = getText("info" + levelNr);

        }
        else {
            //Code for boss
        }
    }


    String getText(String content) {
        setCorrectContent(content);

        String question = "";
        String s = "";
        try {
            BufferedReader fw = new BufferedReader(new FileReader(new File("test.txt")));

            String lineOfContent = fw.readLine();
            while(lineOfContent != null) {
                switch (lineOfContent){
                    case "[Question1]": trueIfWantedContent = q11;
                        break;
                    case "[Answer1]": trueIfWantedContent = a11;
                        break;
                    case "[Info1]": trueIfWantedContent = i11;
                        break;
                    case "[Question2]": trueIfWantedContent = q12;
                        break;
                    case "[Answer2]": trueIfWantedContent = a12;
                        break;
                    case "[Info2]": trueIfWantedContent = i12;
                        break;
                    case "[Question3]": trueIfWantedContent = q13;
                        break;
                    case "[Answer3]": trueIfWantedContent = a13;
                        break;
                    case "[Info3]": trueIfWantedContent = i13;
                        break;
                    case "[Question4]": trueIfWantedContent = q14;
                        break;
                    case "[Answer4]": trueIfWantedContent = a14;
                        break;
                    case "[Info4]": trueIfWantedContent = i14;
                        break;
                    case "[Question5]": trueIfWantedContent = q21;
                        break;
                    case "[Answer5]": trueIfWantedContent = a21;
                        break;
                    case "[Info5]": trueIfWantedContent = i21;
                        break;
                    case "[Question6]": trueIfWantedContent = q22;
                        break;
                    case "[Answer6]": trueIfWantedContent = a22;
                        break;
                    case "[Info6]": trueIfWantedContent = i22;
                        break;
                    case "[Question7]": trueIfWantedContent = q23;
                        break;
                    case "[Answer7]": trueIfWantedContent = a23;
                        break;
                    case "[Info7]": trueIfWantedContent = i23;
                        break;
                    case "[Question8]": trueIfWantedContent = q24;
                        break;
                    case "[Answer8]": trueIfWantedContent = a24;
                        break;
                    case "[Info8]": trueIfWantedContent = i24;
                        break;
                    case "[Question9]": trueIfWantedContent = q31;
                        break;
                    case "[Answer9]": trueIfWantedContent = a31;
                        break;
                    case "[Info9]": trueIfWantedContent = i31;
                        break;
                    case "[Question10]": trueIfWantedContent = q32;
                        break;
                    case "[Answer10]": trueIfWantedContent = a32;
                        break;
                    case "[Info10]": trueIfWantedContent = i32;
                        break;
                    case "[Question11]": trueIfWantedContent = q33;
                        break;
                    case "[Answer11]": trueIfWantedContent = a33;
                        break;
                    case "[Info11]": trueIfWantedContent = i33;
                        break;
                    case "[Question12]": trueIfWantedContent = q34;
                        break;
                    case "[Answer12]": trueIfWantedContent = a34;
                        break;
                    case "[Info12]": trueIfWantedContent = i34;
                        break;
                }
                if (trueIfWantedContent) {
                    s = s + lineOfContent.replaceAll("\\[.*?\\]", "");
                }
                lineOfContent = fw.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(s);
        return question;
    }



    private void setCorrectContent(String content){
        switch (content){
            case "question1": q11 = true;
                break;
            case "answer1": a11 = true;
                break;
            case "info1": i11 = true;
                break;
            case "question2": q12 = true;
                break;
            case "answer2": a12 = true;
                break;
            case "info2": i12 = true;
                break;
            case "question3": q13 = true;
                break;
            case "answer3": a13 = true;
                break;
            case "info3": i13 = true;
                break;
            case "question4": q14 = true;
                break;
            case "answer4": a14 = true;
                break;
            case "info4": i14 = true;
                break;
            case "question5": q21 = true;
                break;
            case "answer5": a21 = true;
                break;
            case "info5": i21 = true;
                break;
            case "question6": q22 = true;
                break;
            case "answer6": a22 = true;
                break;
            case "info6": i22 = true;
                break;
            case "question7": q23 = true;
                break;
            case "answer7": a23 = true;
                break;
            case "info7": i23 = true;
                break;
            case "question8": q24 = true;
                break;
            case "answer8": a24 = true;
                break;
            case "info8": i24 = true;
                break;
            case "question9": q31 = true;
                break;
            case "answer9": a31 = true;
                break;
            case "info9": i31 = true;
                break;
            case "question10": q32 = true;
                break;
            case "answer10": a32 = true;
                break;
            case "info10": i32 = true;
                break;
            case "question11": q33 = true;
                break;
            case "answer11": a33 = true;
                break;
            case "info11": i33 = true;
                break;
            case "question12": q34 = true;
                break;
            case "answer12": a34 = true;
                break;
            case "info12": i34 = true;
                break;
        }

    }

    public String getLevelInfo(){
        return this.levelInfo;
    }

    public Query getQuery(){
        return this.query;
    }
}
