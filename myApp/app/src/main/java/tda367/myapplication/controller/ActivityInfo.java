package tda367.myapplication.controller;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tda367.myapplication.model.LevelModel;
import tda367.myapplication.model.AccountManager;
import tda367.myapplication.R;
import tda367.myapplication.model.Query;

/** @author Madeleine Lexén
This class is responsible for handling the events generated by the info view
Uses LevelModel, activity_info.xml, toolbar_layout, AccountManager, User, Statistics
Used by LevelActivity
 */

public class ActivityInfo extends AppCompatActivity {

    LevelModel levelModel = LevelModel.getInstance();
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Button btn = (Button) findViewById(R.id.button3);
        textView = (TextView) findViewById(R.id.infoText);
        textView.setMovementMethod(new ScrollingMovementMethod());

        //Sets the toolbar and enables upnavigation, and sets the title
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarActivities);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getLevelTitle());

        setInfoText();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountManager.getInstance().getActiveUser().getUserStatistics().startTimer();
                launchIntent();
            }
        });
    }


    //Launches the right kind of question activity
    private void launchIntent(){
        Intent intent;
        if(levelModel.getCurrentLevel() == 4){
            intent = new Intent(ActivityInfo.this, WriteCode.class);
            startActivity(intent);
        }
        else if(levelModel.getCurrentLevel() % 2 == 0){
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
        Query[] query = levelModel.getLevelMap().get("category" + levelModel.getCurrentCategory());
        return query[levelModel.getCurrentLevel()].getHeading();
    }

    //sets the info text
    public void setInfoText() {
        Query[] query = levelModel.getLevelMap().get("category" + levelModel.getCurrentCategory());
        textView.setText(query[levelModel.getCurrentLevel()].getInfo());
    }

}
