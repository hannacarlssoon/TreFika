package tda367.myapplication.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import tda367.myapplication.R;

/**
 * @author Sara Kitzing
 * This class handels setting the PassedLevel view
 */

public class PassedLevel extends AppCompatActivity {
    //TODO Add saveStatistics

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarActivities);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();*/
        setContentView(R.layout.activity_passed_level);



        Button bbtn = (Button)findViewById(R.id.backButton);


        bbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PassedLevel.this, LevelActivity.class));
            }
        });

        Button nbtn = (Button)findViewById(R.id.nextButton);

        nbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PassedLevel.this, LevelActivity.class));
            }
        });


        // Making the window smaller
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8), (int)(height*0.4));

    }
}
