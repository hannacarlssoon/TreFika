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
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import tda367.myapplication.model.AccountManager;
import tda367.myapplication.model.LearnJava;
import tda367.myapplication.model.LevelModel;
import tda367.myapplication.model.MultiChoice;
import tda367.myapplication.model.Query;
import tda367.myapplication.R;

/**
 * @author Madeleine Lexén, revised by Sara Kitzing
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
    private TextView altTextView1;
    private TextView altTextView2;
    private TextView altTextView3;
    private TextView altTextView4;
    private boolean showKey = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_multi_choice);

        setView();

        setToolbar();

        setQuestion();
        setAltTexts();

        setSubmitButton();

        //sets onclicklistener for hint button
        hintButton.setOnClickListener(imgBtnlistener);
    }

    //Sets buttons and views
    private void setView() {
        radioAnswerGroup = (RadioGroup)findViewById(R.id.radioGroup);
        submitButton = (Button)findViewById(R.id.SubmitButton);
        textView = (TextView)findViewById(R.id.questionBox);
        hintButton = (ImageButton)findViewById(R.id.hintButton);
        altTextView1 = (TextView)findViewById(R.id.altOneButton);
        altTextView2 =  (TextView)findViewById(R.id.altTwoButton);
        altTextView3 = (TextView)findViewById(R.id.altThreeButton);
        altTextView4 = (TextView)findViewById(R.id.altFourButton);
        context = this;
    }

    //Sets the toolbar and enables upnavigation, and sets the title
    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarActivities);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Multi-choice question");
    }

    //Sets OnClick-listener for submit button
    private void setSubmitButton() {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(QuestionMultiChoice.this);
                setSelectedAnswer();
                if (radioAnswerButton == null) {
                    setNoInput();
                } else{
                    if (learnJava.getLevelModel().checkAnswer(userAnswer)) {
                        //TODO fix call to save
                        try {
                            AccountManager.getInstance().getActiveUser().getUserStatistics().stopTimer();
                            AccountManager.getInstance().getActiveUser().saveStatistics("", false, showKey);
                        } catch (NullPointerException e) {

                        } finally {
                            setPassedLevel(mBuilder);
                        }
                    } else {
                        setFailedLevel(mBuilder);
                    }
                    AlertDialog dialog = mBuilder.create();
                    dialog.show();
                }
            }
        });
    }

    //Sets FailedLevel view
    private void setFailedLevel(AlertDialog.Builder mBuilder) {
        View mView = getLayoutInflater().inflate(R.layout.activity_failed_level, null);
        mBuilder.setPositiveButton("Pröva igen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        mBuilder.setView(mView);
        mBuilder.setCancelable(false);
    }

    //Sets PassedLevel view
    private void setPassedLevel(AlertDialog.Builder mBuilder) {
        View mView = getLayoutInflater().inflate(R.layout.activity_passed_level, null);

        mBuilder.setPositiveButton("Nästa nivå", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(QuestionMultiChoice.this, ActivityInfo.class));
                learnJava.setCurrentLevel(learnJava.getCurrentLevel() + 1);
            }
        });

        mBuilder.setNeutralButton("Tillbaka", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(QuestionMultiChoice.this, LevelActivity.class));
            }
        });

        mBuilder.setView(mView);
        mBuilder.setCancelable(false);
    }

    //Sets message when no input
    private void setNoInput() {
        Toast toast = Toast.makeText(QuestionMultiChoice.this, "Du måste välja ett alternativ", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 470);
        toast.show();
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

    private void setAltTexts(){
        altTextView1.setText(((MultiChoice)learnJava.getLevelModel().getQuery()).getAlt(0));
        altTextView2.setText(((MultiChoice)learnJava.getLevelModel().getQuery()).getAlt(1));
        altTextView3.setText(((MultiChoice)learnJava.getLevelModel().getQuery()).getAlt(2));
        altTextView4.setText(((MultiChoice)learnJava.getLevelModel().getQuery()).getAlt(3));

    }
}
