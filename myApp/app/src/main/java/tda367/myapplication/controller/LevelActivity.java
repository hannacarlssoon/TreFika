package tda367.myapplication.controller;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;

import tda367.myapplication.model.LearnJava;
import tda367.myapplication.R;

public class LevelActivity extends AppCompatActivity {

    private LearnJava learnJava = LearnJava.getInstance();
    String infoText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        //Sets the toolbar and enables upnavigation, and sets the title
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarActivities);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Levels");

        Button firstBtn = (Button)findViewById(R.id.firstButton);
        Button secondBtn = (Button)findViewById(R.id.secondButton);
        Button thirdBtn = (Button)findViewById(R.id.thirdButton);
        Button fourthBtn = (Button)findViewById(R.id.fourthButton);
        Button bossBtn = (Button)findViewById(R.id.bossButton);

        firstBtn.setOnClickListener(buttonListener);
        secondBtn.setOnClickListener(buttonListener);
        thirdBtn.setOnClickListener(buttonListener);
        fourthBtn.setOnClickListener(buttonListener);
        bossBtn.setOnClickListener(buttonListener);
    }

    public void onButtonClick(View view){

    }

    //Handles the back navigation
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

    private Button.OnClickListener buttonListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(LevelActivity.this, ActivityInfo.class));
            learnJava.setCurrentLevel(getLevelId(v));
        }
    };

    private int getLevelId(View view){
        switch (view.getId()){
            case R.id.firstButton : return 0;
            case R.id.secondButton : return 1;
            case R.id.thirdButton : return 2;
            case R.id.fourthButton : return 3;
        }
       return 4;
    }
}
