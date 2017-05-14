package tda367.myapplication.controller;

import android.content.Intent;
import android.support.v4.app.NavUtils;
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

import static tda367.myapplication.R.id.textView;

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
    private int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_in_the_blanks);

        submit = (Button)findViewById(R.id.fillSubmit);
        hint = (ImageButton)findViewById(R.id.hintButton);
        textAnswer1   = (EditText)findViewById(R.id.textFillAnswer1);
        textAnswer2   = (EditText)findViewById(R.id.textFillAnswer2);
        textAnswer3   = (EditText)findViewById(R.id.textFillAnswer3);
        questionView = (TextView) findViewById(R.id.fillQuestion);

        setQuestionText();

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
                setAnswer();
                //TODO handle no input from user
                if(answer1.equals(null) || answer2.equals(null) || answer3.equals(null)){
                    //todo display a message to the user that there was input missing
                }
                else if(learnJava.getLevelModel().checkAnswer(userAnswer)){
                    startActivity(new Intent(FillInTheBlanks.this, PassedLevel.class));
                }
                else {
                    counter++;
                    switch (counter){
                        case 1 : hint.setVisibility(View.VISIBLE);
                        case 2 : //code for showing key
                    }
                startActivity(new Intent(FillInTheBlanks.this, FailedLevel.class));
                }
            }
        });

        hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        questionView.setText(levelModels[learnJava.getCurrentLevel()].getQuestion());
    }
}
