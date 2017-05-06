package tda367.myapplication.Controller;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import tda367.myapplication.Model.LearnJava;
import tda367.myapplication.Model.LevelModel;

import tda367.myapplication.R;

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
        getSupportActionBar().setTitle("Går det att sätta detta till ämnet?");

        setInfoText();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityInfo.this, FillInTheBlanks.class);
                startActivity(intent);
            }
        });

    }


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

    public void setInfoText() {
        LevelModel[] levelModels = learnJava.levelHashMap.get(learnJava.getCurrentCategory());
        textView.setText(levelModels[learnJava.getCurrentLevel()].getInfo());
    }


}
