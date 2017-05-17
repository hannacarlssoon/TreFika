package tda367.myapplication.controller;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;

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
    private boolean enableLevel1 = false;
    private boolean enableLevel2 = false;
    private boolean enableLevel3 = false;
    private boolean enableLevel4 = false;
    private boolean enableLevel5 = false;
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
        getSupportActionBar().setTitle("Levels");

        //Sets buttons
        firstBtn = (Button)findViewById(R.id.firstButton);
        secondBtn = (Button)findViewById(R.id.secondButton);
        thirdBtn = (Button)findViewById(R.id.thirdButton);
        fourthBtn = (Button)findViewById(R.id.fourthButton);
        bossBtn = (Button)findViewById(R.id.bossButton);

       //Todo koppla ihop med statistiken

        //Disables buttons for levels that aren't unlocked
       enablePassedLevels();

        //sets the title of the enabled buttons
       setTitleOnEnabled();

        //Setting onClickListeners to make buttons clickable
        firstBtn.setOnClickListener(buttonListener);
        secondBtn.setOnClickListener(buttonListener);
        thirdBtn.setOnClickListener(buttonListener);
        fourthBtn.setOnClickListener(buttonListener);
        bossBtn.setOnClickListener(buttonListener);
    }

    //sets the booelan value for the levels passed variable
    private void setPassedLevels(){
        if(learnJava.getCurrentCategory().equals("category1")){
            enableLevel1 = true;
            for( int i = 2; i < 6; i++){
                if (statistics.getStatisticsHint().get(i) != null) {
                    enableLeve
                }
            }

            if(statistics.getStatisticsHint().get(statistics.findIndex("Level12")) != null){
                secondIsEnabled = true;
            }
        }
        else{
        }


    }

    //sets the title of the enabled buttons
    private void setTitleOnEnabled(){
        if(enableLevel2){
            secondBtn.setText("2");
        }
        if(enableLevel3){
            thirdBtn.setText("3");
        }
        if(enableLevel4){
            fourthBtn.setText("4");
        }
        if(enableLevel5){
            bossBtn.setText("Boss");
        }
    }

    //Sets the levels that are unlocked to enabled
    private void enablePassedLevels(){
        setPassedLevels();
        firstBtn.setEnabled(enableLevel1);
        secondBtn.setEnabled(enableLevel2);
        thirdBtn.setEnabled(enableLevel3);
        fourthBtn.setEnabled(enableLevel4);
        bossBtn.setEnabled(enableLevel5);
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
