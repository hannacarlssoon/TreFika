package tda367.myapplication.controller;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
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
import tda367.myapplication.model.LevelModel;
import tda367.myapplication.model.MultiChoice;
import tda367.myapplication.R;
import tda367.myapplication.model.Query;

/**
 * @author Madeleine Lexén, revised by Sara Kitzing and Tobias Lindgren
 * Responsible for handling the events from the multichoice questions view
 * Used by ActivityInfo
 * Uses LevelModel, activity_question_multi_choice.xml, PassedLevel, User, Statistics
 */


public class QuestionMultiChoice extends AppCompatActivity {

    private RadioGroup radioAnswerGroup;
    private RadioButton radioAnswerButton;
    private Button submitButton;
    private ImageButton hintButton;
    private String userAnswer;
    private TextView textView;
    private LevelModel levelModel = LevelModel.getInstance();
    private Context context;
    private TextView altTextView1;
    private TextView altTextView2;
    private TextView altTextView3;
    private TextView altTextView4;
    private boolean showKey = false;
    private boolean keyUsed = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_multi_choice);

        setView();
        setToolbar();
        setQuestion();
        setAltTexts();
        setSubmitButton();

        //Makes the hint button invisible until one wrong answer
        hintButton.setVisibility(View.INVISIBLE);

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
        textView.setMovementMethod(new ScrollingMovementMethod());
    }

    //Sets the toolbar and enables upnavigation, and sets the title
    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarActivities);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Nivå " + (levelModel.getCurrentLevel() + 1));
    }

    //Sets OnClick-listener for submit button
    private void setSubmitButton() {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(QuestionMultiChoice.this);
                setSelectedAnswer();
                if (radioAnswerButton == null) {
                    showNoInputMessage();
                } else{
                    if (levelModel.getQuery().checkAnswer(userAnswer)) {
                        try {
                           AccountManager.getInstance().getActiveUser().getUserStatistics().stopTimer();
                            int level = levelModel.getCurrentLevel() + 1;
                            AccountManager.getInstance().getActiveUser().saveStatistics("category" + levelModel.getCurrentCategory() + level, keyUsed, showKey);
                            LevelActivity lv = new LevelActivity();
                            lv.enablePassedLevels();
                        } catch (NullPointerException e) {

                        } finally {
                            //Sets PassedLevel
                            new PassedLevel(QuestionMultiChoice.this);
                        }
                    } else {
                        setFailedLevel(mBuilder);
                        hintButton.setVisibility(View.VISIBLE);
                    }

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
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }

    //Sets message when no input
    private void showNoInputMessage() {
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
        }

    }


    //Method for setting the right question to the textView
    private void setQuestion(){
        Query[] query = levelModel.getLevelMap().get("category" + levelModel.getCurrentCategory());
        textView.setText(query[levelModel.getCurrentLevel()].getQuestion());
    }

    //Defines onclicklistener for hint button
    private ImageButton.OnClickListener imgBtnlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!showKey) {
                createDialog(levelModel.getQuery().getHint());
                showKey = true;
            }
            else {
                keyUsed = true;
                createDialog(levelModel.getQuery().getHint() + "\n \nFacit: \n"+ levelModel.getQuery().getAnswer());
            }
        }
    };

    //Method for creating dialog and displaying hint
    private void createDialog(String hint){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        TextView textView = new TextView(context);
        textView.setText(hint);
        textView.setTextSize(15);

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

    //Sets the alternatives for the multiple choice
    private void setAltTexts(){
        altTextView1.setText(((MultiChoice)levelModel.getQuery()).getAlt(0));
        altTextView2.setText(((MultiChoice)levelModel.getQuery()).getAlt(1));
        altTextView3.setText(((MultiChoice)levelModel.getQuery()).getAlt(2));
        altTextView4.setText(((MultiChoice)levelModel.getQuery()).getAlt(3));

    }
}
