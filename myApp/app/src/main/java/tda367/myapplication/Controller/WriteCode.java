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

import tda367.myapplication.R;

public class WriteCode extends AppCompatActivity {
    Button submit;
    EditText userCode;
    private String answer;
    //private String codeResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_code);

        submit = (Button)findViewById(R.id.codeSubmit);
        userCode   = (EditText)findViewById(R.id.codeEditText);


        //Sets the toolbar and enables upnavigation, and sets the title
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarActivities);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Write code");



        //Checks if answer is right, sets PassedLevelview if correct, otherwise FailedLevel
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnswer();
                //TODO handle no input from user
                //TODO add the actual call to WriteCide
                //if(chechAnswer(answer)){
                startActivity(new Intent(WriteCode.this, PassedLevel.class));
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
        answer = WriteCode.this.userCode.getText().toString();

    }

}
