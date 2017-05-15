package tda367.myapplication.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NavUtils;
//import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import tda367.myapplication.R;
import tda367.myapplication.model.LearnJava;
import tda367.myapplication.model.LevelModel;


/**
 * @author Sara Kitzing, revised by Madeleine Lexén
 * This class is responsible for handling the events from te fill in the blanks question view
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
    private boolean counter = false;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_in_the_blanks);
        context = this;

        //Sets buttons and views
        submit = (Button)findViewById(R.id.fillSubmit);
        hint = (ImageButton)findViewById(R.id.hintButton);
        textAnswer1   = (EditText)findViewById(R.id.textFillAnswer1);
        textAnswer2   = (EditText)findViewById(R.id.textFillAnswer2);
        textAnswer3   = (EditText)findViewById(R.id.textFillAnswer3);
        questionView = (TextView) findViewById(R.id.fillQuestion);

        setQuestionText();

        //Making the hint button invisible at start
        hint.setVisibility(View.INVISIBLE);

        //Sets the toolbar and enables upnavigation, and sets the title
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarActivities);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Fill in the blanks");



        //Checks if answer is right, sets PassedLevelview if correct, otherwise FailedLevel
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               AlertDialog.Builder mBuilder = new AlertDialog.Builder(FillInTheBlanks.this);
                View mView;
                setAnswer();
                //TODO handle no input from user
                if(answer1.equals(null) || answer2.equals(null) || answer3.equals(null)){
                    //todo display a message to the user that there was input missing
                }
                else if(learnJava.getLevelModel().checkAnswer(userAnswer)){
                    mView = getLayoutInflater().inflate(R.layout.activity_passed_level, null);
                            Button next = (Button) mView.findViewById(R.id.nextButton);
                            Button back = (Button) mView.findViewById(R.id.backButton);

                            next.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //TODO show the next question in line
                                    System.out.println("Click click");
                                }
                            });
                           back.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //TODO back to category
                                    System.out.println("Click back");
                                        }
                                    });
                    mBuilder.setView(mView);
                    //startActivity(new Intent(FillInTheBlanks.this, PassedLevel.class));
                }
                else {
                    hint.setVisibility(View.VISIBLE);
                 /*   counter++;
                    switch (counter){
                        case 1 : hint.setVisibility(View.VISIBLE);
                        case 2 : //code for showing key
                    }
                    */
                    mView = getLayoutInflater().inflate(R.layout.activity_failed_level, null);
                    Button tryAgan = (Button) mView.findViewById(R.id.tryAgain);

                    tryAgan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //TODO show same question again
                            System.out.println("Try again click");
                        }
                    });
                    mBuilder.setView(mView);
                //startActivity(new Intent(FillInTheBlanks.this, FailedLevel.class));

                }
                mBuilder.setCancelable(false);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });

        //onClickListener fo hint button and creates dialog for showing hint
        hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                TextView textView = new TextView(context);
                textView.setText(learnJava.getLevelModel().getHint());

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
        });
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
        //todo remove the whitespace from the input
       answer1 = FillInTheBlanks.this.textAnswer1.getText().toString();
       answer2 = FillInTheBlanks.this.textAnswer2.getText().toString();
       answer3 = FillInTheBlanks.this.textAnswer3.getText().toString();

       userAnswer = answer1 + "," + answer2 + "," + answer3;
    }

    public void setQuestionText() {
        LevelModel[] levelModels = learnJava.getLevelHashMap().get(learnJava.getCurrentCategory());
        questionView.setText(levelModels[learnJava.getCurrentLevel()].getQuery().getQuestion());
    }
}
