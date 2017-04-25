package tda367.myapplication.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import tda367.myapplication.Model.LevelModel;
import tda367.myapplication.R;

public class LevelActivity extends AppCompatActivity {

    private static final List<LevelModel> list = new ArrayList<>();
    private TextView textView;
    String infoText;
    ActivityInfo ai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        addContentToList();

        Button btn = (Button)findViewById(R.id.firstButton);
        ai = new ActivityInfo();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LevelActivity.this, ActivityInfo.class));
                //ai.setInfoText();
            }
        });

    }

    private void addContentToList(){
        for (int i = 0; i < 12; i++) {
            LevelModel levelModel = new LevelModel(i, getQuestionText(i), getAnswerText(i), getInfoText(i));
            list.add(levelModel);
        }
    }

    public void onButtonClick(View view){
        update();
    }

    public void update() {
        LevelModel lm = getItemFromList(0);
        infoText = lm.getInfo();
        textView.setText(infoText);
    }

    /*public LevelModel getLevelModel(){
        return this.levelModel;
    }*/

    private String getQuestionText(int number){
        String string = "";
        int counter = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getAssets().open("test.txt")));
            String lineOfContent = bufferedReader.readLine();
            while (lineOfContent != null){
                counter++;
                if (counter == 2 + (number * 6)){
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

    private String getAnswerText(int number){
        String string = "";
        int counter = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getAssets().open("test.txt")));
            String lineOfContent = bufferedReader.readLine();
            while (lineOfContent != null){
                counter++;
                if (counter == 4 + (number * 6)){
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

    private String getInfoText(int number){
        String string = "";
        int counter = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getAssets().open("test.txt")));
            String lineOfContent = bufferedReader.readLine();
            while (lineOfContent != null){
                counter++;
                if (counter == 6 + (number * 6)){
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

    public static LevelModel getItemFromList(int i){
        return list.get(i);
    }

}
