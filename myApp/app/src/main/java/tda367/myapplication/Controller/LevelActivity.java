package tda367.myapplication.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import tda367.myapplication.Model.LevelModel;
import tda367.myapplication.R;

public class LevelActivity extends AppCompatActivity {

    private LevelModel levelModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        Button btn = (Button)findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LevelActivity.this, ActivityInfo.class));
            }
        });
    }

    public void onButtonClick(View view){
        Button btn = (Button)findViewById(R.id.button);
        this.levelModel = new LevelModel(btn.getText().toString(), "1");
        TextView textView = (TextView)findViewById(R.id.textView8);
        textView.setText(levelModel.getQuestionInfo());
    }

    public LevelModel getLevelModel(){
        return this.levelModel;
    }

}
