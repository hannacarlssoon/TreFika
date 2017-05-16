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
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import tda367.myapplication.model.LearnJava;
import tda367.myapplication.model.LevelModel;
import tda367.myapplication.model.Query;
import tda367.myapplication.R;

/**
 * @author Madeleine Lexén
 * this class handles the events from the multichoice questions view
 * uses LearnJava
 */

public class QuestionMultiChoice extends AppCompatActivity {

    private RadioGroup radioAnswerGroup;
    private RadioButton radioAnswerButton;
    private Button submitButton;
    private ImageButton hintButton;
    private String userAnswer;
    private TextView textView;
    private LearnJava learnJava = LearnJava.getInstance();
    private Context context;
    private boolean showKey = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_multi_choice);

        //Sets buttons and views
        radioAnswerGroup = (RadioGroup)findViewById(R.id.radioGroup);
        submitButton = (Button)findViewById(R.id.SubmitButton);
        textView = (TextView)findViewById(R.id.questionBox);
        hintButton = (ImageButton)findViewById(R.id.hintButton);
        context = this;


        //Sets the toolbar and enables upnavigation, and sets the title
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarActivities);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Multi-choice question");

        setQuestion();

        //sets listener on submitbutton, checks if answer is correct,
        // changes view to passedLevel if correct, otherwise to FailedLevel.
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(QuestionMultiChoice.this);
                View mView;
                setSelectedAnswer();
                //TODO handle no input from user
                if (radioAnswerButton == null) {
                    //todo display a message to the user that there was input missing
                }
                else if(learnJava.getLevelModel().checkAnswer(userAnswer)){
                    mView = getLayoutInflater().inflate(R.layout.activity_passed_level, null);
                    Button next = (Button) mView.findViewById(R.id.nextButton);
                    Button back = (Button) mView.findViewById(R.id.backButton);

                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(QuestionMultiChoice.this,ActivityInfo.class));
                            learnJava.setCurrentLevel(learnJava.getCurrentLevel() + 1);
                        }
                    });
                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(QuestionMultiChoice.this, LevelActivity.class));
                        }
                    });
                    mBuilder.setView(mView);
                    mBuilder.setCancelable(false);
                }
                else {
                    mView = getLayoutInflater().inflate(R.layout.activity_failed_level, null);
                    mBuilder.setPositiveButton("Pröva igen", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    /*Button tryAgan = (Button) mView.findViewById(R.id.tryAgain);

                    tryAgan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            System.out.println("Try again click");
                        }
                    });
                    */
                    mBuilder.setView(mView);
                    mBuilder.setCancelable(false);
                    //startActivity(new Intent(QuestionMultiChoice.this, FailedLevel.class));
                }
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });

        //sets onclicklistener for hint button
        hintButton.setOnClickListener(imgBtnlistener);
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

    //Gets the selected radiobutton from the view, and sets that alternative as the answer.
    protected void setSelectedAnswer() {
        try {
            if(radioAnswerGroup.getCheckedRadioButtonId() == -1){
                throw new IllegalStateException();
            }
            int selectedId = radioAnswerGroup.getCheckedRadioButtonId();
            radioAnswerButton = (RadioButton) findViewById(selectedId);
            userAnswer = (String) radioAnswerButton.getText();
        }
        catch(IllegalStateException e){
            //öppna popup ruta med felmeddelande till användaren?
        }

        System.out.println(userAnswer);
    }

    //Method for setting the right question to the textView
    private void setQuestion(){
        LevelModel[] levelModels = learnJava.getLevelHashMap().get(learnJava.getCurrentCategory());
        textView.setText(levelModels[learnJava.getCurrentLevel()].getQuery().getQuestion());
    }

    //Defines onclicklistener for hint button
    private ImageButton.OnClickListener imgBtnlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!showKey) {
                createDialog(learnJava.getLevelModel().getHint());
                showKey = true;
            }
            else {
                createDialog(learnJava.getLevelModel().getHint() + "\n"+ learnJava.getLevelModel().getQuery().getAnswer());
            }
        }
    };

    //Method for creating dialog and displaying hint
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
