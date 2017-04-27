package tda367.myapplication.Controller;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import tda367.myapplication.Model.LevelModel;
import tda367.myapplication.R;

public class QuestionMultiChoice extends AppCompatActivity {

    private RadioGroup radioAnswerGroup;
    private RadioButton radioAnswerButton;
    private Button submitButton;
    private String answer;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_multi_choice);
        radioAnswerGroup = (RadioGroup)findViewById(R.id.radioGroup);
        Button btn = (Button)findViewById(R.id.SubmitButton);
        textView = (TextView)findViewById(R.id.questionBox);

        setQuestionText();

        System.out.println(getIntent().getStringExtra("ARG_QUESTION"));

        //Sets the toolbar and enables upnavigation, and sets the title
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarActivities);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Multi-choice question");

        //sets listener on submitbutton, checks if answer is correct,
        // changes view to passedLevel if correct, otherwise to FailedLevel.
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnswer();
                // if(checkAnswer()){
                startActivity(new Intent(QuestionMultiChoice.this, PassedLevel.class));
            /* }
            else{
            startActivity(new Intent(QuestionMultiChoice.this, FailedLevel.class));
            }
             */
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

    //Gets the selected radiobutton from the view, and sets that alternative as the answer.
    protected void setAnswer(){
        int selectedId = radioAnswerGroup.getCheckedRadioButtonId();
        radioAnswerButton = (RadioButton)findViewById(selectedId);
        answer = (String) radioAnswerButton.getText();
        System.out.println(answer);
    }

    private void setQuestionText(){
        LevelModel lm = LevelActivity.getItemFromList(0);
        String infoText = lm.getQuestion();
        textView.setText(infoText);
    }




}
