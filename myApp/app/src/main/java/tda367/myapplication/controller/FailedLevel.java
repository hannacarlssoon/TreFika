package tda367.myapplication.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import tda367.myapplication.R;
import tda367.myapplication.model.LearnJava;
import tda367.myapplication.model.MultiChoice;


/**
 * @author Sara Kitzing
 * This class handles setting the FailedLevel view
 */

public class FailedLevel {
    //extends AppCompatActivity implements View.OnClickListener
/*
    private Button tryAgain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getSupportActionBar().hide();
        setContentView(R.layout.activity_failed_level);

        tryAgain = (Button) findViewById(R.id.tryAgain);

        // Making the window smaller
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8), (int)(height*0.4));

        tryAgain.setOnClickListener(this);
    }


    //This method doesn't work, needs to be worked on
    @Override
    public void onClick(View v) {
        if(LearnJava.getInstance().getQuery().toString().equals("MultiChoice")){
            startActivity(new Intent(FailedLevel.this, QuestionMultiChoice.class));
        }
        else if(LearnJava.getInstance().getQuery().toString().equals("ModelFillBlanks")) {
            startActivity(new Intent(FailedLevel.this, FillInTheBlanks.class));
        }
    }
    */
}
