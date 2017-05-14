package tda367.myapplication.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import tda367.myapplication.R;
import tda367.myapplication.model.LearnJava;

public class HintActivity extends AppCompatActivity implements View.OnClickListener {

    TextView hintTextView;
    LearnJava learnJava = LearnJava.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        TextView textView = new TextView(this);
        textView.setText(learnJava.getLevelModel().getHint());
        EditText editText = new EditText(this);

        alertDialogBuilder.setView(textView);
        alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setView(textView, 20, 20, 20, 20);
        alertDialog.show();

        /*hintTextView = (TextView)findViewById(R.id.hintTextView);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int)(width*0.8), (int)(height*0.4));

        hintTextView.setText(learnJava.getLevelModel().getHint());*/
    }

    @Override
    public void onClick(View v) {

    }
}
