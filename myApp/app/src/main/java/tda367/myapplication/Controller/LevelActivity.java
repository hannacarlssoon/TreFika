package tda367.myapplication.Controller;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import tda367.myapplication.Model.LevelModel;
import tda367.myapplication.R;

public class LevelActivity extends AppCompatActivity {

    String infoText;
    ActivityInfo ai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        Button btn = (Button)findViewById(R.id.firstButton);
        ai = new ActivityInfo();

        //Sets the toolbar and enables upnavigation, and sets the title
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarActivities);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Levels");


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LevelActivity.this, ActivityInfo.class));
                //ai.setInfoText();
            }
        });

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

}
