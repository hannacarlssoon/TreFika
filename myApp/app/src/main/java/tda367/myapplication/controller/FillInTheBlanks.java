package tda367.myapplication.controller;


import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import android.widget.TextView;
import android.widget.Toast;

import tda367.myapplication.R;
import tda367.myapplication.model.AccountManager;
import tda367.myapplication.model.LearnJava;
import tda367.myapplication.model.LevelModel;

/**
 * @author Sara Kitzing, revised by Madeleine Lexén and Tobias Lindgren
 * Responsible for handling the events from the fill in the blanks question view
 * Used by ActivityInfo
 * Uses LearnJava, activity_fill_in_the_blanks.xml, PassedLevel
 */

public class FillInTheBlanks extends AppCompatActivity {
    Button submit;
    ImageButton hint;
    EditText textAnswer1;
    EditText textAnswer2;
    EditText textAnswer3;
    private String userAnswer;
    private String answer1;
    private String answer2;
    private String answer3;
    private TextView questionView;
    private LearnJava learnJava = LearnJava.getInstance();
    private boolean showKey = false;
    private boolean keyUsed = false;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_in_the_blanks);
        context = this;

        setView();
        setToolbar();
        setSubmitButton();
        setQuestionText();

        //Making the hint button invisible at start
        hint.setVisibility(View.INVISIBLE);

        //set onclicklistener for hint button
        hint.setOnClickListener(imgBtnListener);
    }

    //Sets buttons and views
    private void setView() {
        submit = (Button)findViewById(R.id.fillSubmit);
        hint = (ImageButton)findViewById(R.id.hintButton);
        textAnswer1   = (EditText)findViewById(R.id.textFillAnswer1);
        textAnswer2   = (EditText)findViewById(R.id.textFillAnswer2);
        textAnswer3   = (EditText)findViewById(R.id.textFillAnswer3);
        questionView = (TextView) findViewById(R.id.fillQuestion);
    }

    //Sets the toolbar, enables upnavigation, and sets the title
    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarActivities);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Fill in the blanks");
    }

    //Sets OnClick-listener for submit button
    private void setSubmitButton(){
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(FillInTheBlanks.this);
                setAnswer();
                if (answer1.isEmpty() || answer2.isEmpty() || answer3.isEmpty()) {
                    showNoInputMessage();
                } else {
                    if (learnJava.getLevelModel().checkAnswer(userAnswer)) {
                        try {
                            AccountManager.getInstance().getActiveUser().getUserStatistics().stopTimer();
                            int level = LearnJava.getInstance().getCurrentLevel() + 1;
                            AccountManager.getInstance().getActiveUser().saveStatistics("category" + LearnJava.getInstance().getCurrentCategory() + level, keyUsed, showKey);
                            System.out.println("KOm hit pls");
                            LevelActivity lv = new LevelActivity();
                            lv.enablePassedLevels();
                        } catch (NullPointerException e) {
                            System.out.println("den hamnade i catch på fill in the blanks");

                        } finally {
                            //Sets PassedLevel-view
                            new PassedLevel(FillInTheBlanks.this);
                        }

                    } else {
                        setFailedLevel(mBuilder);
                        hint.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    //Shows message when no input
    private void showNoInputMessage(){
        Toast toast = Toast.makeText(FillInTheBlanks.this, "Input saknas", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 470);
        toast.show();
    }

    //Sets FailedLevel viewn
    private void setFailedLevel(AlertDialog.Builder mBuilder){
        View mView = getLayoutInflater().inflate(R.layout.activity_failed_level, null);
        mBuilder.setPositiveButton("Pröva igen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        mBuilder.setView(mView);
        mBuilder.setCancelable(false);
        AlertDialog dialog = mBuilder.create();
        dialog.show();
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

    //Sets answer to one string
    public void setAnswer(){
       answer1 = FillInTheBlanks.this.textAnswer1.getText().toString().trim();
       answer2 = FillInTheBlanks.this.textAnswer2.getText().toString().trim();
       answer3 = FillInTheBlanks.this.textAnswer3.getText().toString().trim();

       userAnswer = answer1 + "," + answer2 + "," + answer3;
    }

    //Method for setting the right question to the textView
    public void setQuestionText() {
        LevelModel[] levelModels = learnJava.getLevelHashMap().get("category" + learnJava.getCurrentCategory());
        questionView.setText(levelModels[learnJava.getCurrentLevel()].getQuery().getQuestion());
    }

    //Defines onclicklistener for hint button
    private ImageButton.OnClickListener imgBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!showKey){
                createDialog(learnJava.getLevelModel().getHint());
                showKey = true;
            }
            else {
                keyUsed = true;
                createDialog(learnJava.getLevelModel().getHint() + "\n \nFacit: \n" + learnJava.getLevelModel().getQuery().getAnswer());
            }
        }
    };

    //Method for creating dialog an displaying hint
    private void createDialog(String hint){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        TextView textView = new TextView(context);
        textView.setText(hint);

        alertDialogBuilder.setView(textView);
        alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setView(textView, 20, 20, 20, 20);
        alertDialog.show();
    }
}
