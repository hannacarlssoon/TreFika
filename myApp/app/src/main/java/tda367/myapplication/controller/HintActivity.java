package tda367.myapplication.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import tda367.myapplication.R;
import tda367.myapplication.model.LearnJava;

public class HintActivity extends AppCompatActivity {

    TextView hintTextView;
    LearnJava learnJava = LearnJava.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        hintTextView = (TextView)findViewById(R.id.hintTextView);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int)(width*0.8), (int)(height*0.4));

        hintTextView.setText(learnJava.getLevelModel().getHint());
    }
}
