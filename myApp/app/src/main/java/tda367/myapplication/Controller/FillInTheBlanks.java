package tda367.myapplication.Controller;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import tda367.myapplication.Model.ModelFillBlanks;
import tda367.myapplication.R;

public class FillInTheBlanks extends AppCompatActivity {
    Button submit;
    EditText textAnswer1;
    EditText textAnswer2;
    EditText textAnswer3;
    private String answer;
    private String answer1;
    private String answer2;
    private String answer3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_in_the_blanks);

        submit = (Button)findViewById(R.id.fillSubmit);
        textAnswer1   = (EditText)findViewById(R.id.textFillAnswer1);
        textAnswer2   = (EditText)findViewById(R.id.textFillAnswer2);
        textAnswer3   = (EditText)findViewById(R.id.textFillAnswer3);

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
                //TODO add the actual call to ModelFillBlanks
                //if(chechAnswer(answer)){
                startActivity(new Intent(FillInTheBlanks.this, PassedLevel.class));
              /*   }
                else {
                startActivity(new Intent(FillInTheBlanks.this, FailedLevel.class));
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

    //Sets answer to one string
    public void setAnswer(){
       answer1 = FillInTheBlanks.this.textAnswer1.getText().toString();
       answer2 = FillInTheBlanks.this.textAnswer2.getText().toString();
       answer3 = FillInTheBlanks.this.textAnswer3.getText().toString();

       answer = answer1 + "," + answer2 + "," + answer3;
    }
}
