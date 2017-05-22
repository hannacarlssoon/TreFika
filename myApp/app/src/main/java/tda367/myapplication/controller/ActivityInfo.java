package tda367.myapplication.controller;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import tda367.myapplication.model.AccountManager;
import tda367.myapplication.model.LearnJava;
import tda367.myapplication.model.LevelModel;

import tda367.myapplication.R;
import tda367.myapplication.model.MultiChoice;

/* @author Madeleine Lexén
This class is responsible for handling the events generated by the info view
Uses LearnJava
 */

public class ActivityInfo extends AppCompatActivity {

    LearnJava learnJava = LearnJava.getInstance();
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        //getActionBar().setDisplayHomeAsUpEnabled(true);


        Button btn = (Button) findViewById(R.id.button3);
        textView = (TextView) findViewById(R.id.infoText);


        //Sets the toolbar and enables upnavigation, and sets the title
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarActivities);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getLevelTitle());

        setInfoText();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AccountManager.getInstance().getActiveUser().getUserStatistics().startTimer();
                } catch (NullPointerException e) {

                } finally {
                    launchIntent();
                }
            }
        });
    }


    //Launches the right kind of question activity
    private void launchIntent(){
        Intent intent;
        if(learnJava.getLevelModel().getQuestionNumber() == 5){
            //launch boss question
            intent = new Intent(ActivityInfo.this, WriteCode.class);
            startActivity(intent);
        }
        else if(learnJava.getLevelModel().getQuestionNumber() % 2 == 1){
            intent = new Intent(ActivityInfo.this, FillInTheBlanks.class);
            startActivity(intent);
        }
        else{
            intent = new Intent(ActivityInfo.this, QuestionMultiChoice.class);
            startActivity(intent);
        }
    }


    // Handles the upnavigation
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //gets the title for the level info view
    private String getLevelTitle(){
        LevelModel[] levelModels = learnJava.getLevelHashMap().get("category" + learnJava.getCurrentCategory());
        return levelModels[learnJava.getCurrentLevel()].getHeading();
    }

    //sets the info text
    public void setInfoText() {
        LevelModel[] levelModels = learnJava.getLevelHashMap().get("category" + learnJava.getCurrentCategory());
        textView.setText(levelModels[learnJava.getCurrentLevel()].getInfo());
    }

}
