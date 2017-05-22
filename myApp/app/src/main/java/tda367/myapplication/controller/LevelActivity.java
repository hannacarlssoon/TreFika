package tda367.myapplication.controller;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;

import tda367.myapplication.model.AccountManager;
import tda367.myapplication.model.LearnJava;
import tda367.myapplication.R;
import tda367.myapplication.model.Statistics;

/* @author Tobias Lindgren, revised by Madeleine Lexén
Uses learnJava
This class is responsible for handling the actions from the levelActivity view
 */

public class LevelActivity extends AppCompatActivity {

    private LearnJava learnJava = LearnJava.getInstance();
    private boolean[] enabledLevels = new boolean[6];
    private Button bossBtn;
    private Button fourthBtn;
    private Button thirdBtn;
    private Button secondBtn;
    private Button firstBtn;

    private Statistics statistics = AccountManager.getInstance().getActiveUser().getUserStatistics();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        //Sets the toolbar and enables upnavigation, and sets the title
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarActivities);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Kategorie " + learnJava.getCurrentCategory());

        //Sets buttons
        firstBtn = (Button)findViewById(R.id.firstButton);
        secondBtn = (Button)findViewById(R.id.secondButton);
        thirdBtn = (Button)findViewById(R.id.thirdButton);
        fourthBtn = (Button)findViewById(R.id.fourthButton);
        bossBtn = (Button)findViewById(R.id.bossButton);

        //Disables buttons for levels that aren't unlocked
       enablePassedLevels();

        //Setting onClickListeners to make buttons clickable
        firstBtn.setOnClickListener(buttonListener);
        secondBtn.setOnClickListener(buttonListener);
        thirdBtn.setOnClickListener(buttonListener);
        fourthBtn.setOnClickListener(buttonListener);
        bossBtn.setOnClickListener(buttonListener);
    }

    //sets the booelan value for the levels passed variable
    private void setPassedLevels(){
        //TODO make i dynamic with amount of level, more exstendible
        for (int i = 1; i < 5; i++){
            try {
                statistics.getStatisticsHint().get(statistics.findIndex("category" + learnJava.getCurrentCategory() + (learnJava.getCurrentLevel() + i)));
                enabledLevels[i] = true;
            }
            catch (IndexOutOfBoundsException e){
                System.out.println("In catch in levelActivity");
                enabledLevels[i] = false;
            }
        }
        enabledLevels[0] = true;
    }

    //sets the title of the enabled buttons
    private void setTitleOnEnabled(){
        if(enabledLevels[1]){
            secondBtn.setText("2");
        }
        if(enabledLevels[2]){
            thirdBtn.setText("3");
        }
        if(enabledLevels[3]){
            fourthBtn.setText("4");
        }
        if(enabledLevels[4]){
            bossBtn.setText("Boss");
        }
    }

    //Sets the levels that are unlocked to enabled
    public void enablePassedLevels(){
        setPassedLevels();
        firstBtn.setEnabled(enabledLevels[0]);
        secondBtn.setEnabled(enabledLevels[1]);
        thirdBtn.setEnabled(enabledLevels[2]);
        fourthBtn.setEnabled(enabledLevels[3]);
        bossBtn.setEnabled(enabledLevels[4]);
        setTitleOnEnabled();
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

    //Definition for the onClickListeners for all buttons
    private Button.OnClickListener buttonListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(LevelActivity.this, ActivityInfo.class));
            learnJava.setCurrentLevel(getLevelId(v));
        }
    };

    //Method for getting the level ID
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
