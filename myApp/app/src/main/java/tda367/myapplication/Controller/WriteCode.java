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
import tda367.myapplication.service.Server;

/**
 * @author Sara Kitzing
 * This class handles the call to the WriteCode-model, setting the writeCode view and creating and
 * calling the server to compile the users code
 */

public class WriteCode extends AppCompatActivity {
    Button submit;
    EditText userCode;
    private String answer;
    private tda367.myapplication.model.WriteCode writeCode;
    private Server server;
    //private String codeResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_code);

        submit = (Button)findViewById(R.id.codeSubmit);
        userCode   = (EditText)findViewById(R.id.codeEditText);
        server = new Server("127.0.0.1");
        //TODO actual question and answer --> create the object right
        writeCode = new tda367.myapplication.model.WriteCode("hej", "då");


        //Sets the toolbar and enables upnavigation, and sets the title
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarActivities);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Write code");



        //Compiles code and checks if answer is right, sets PassedLevelview if correct, otherwise FailedLevel
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnswer();
                runServer();
                writeCode.setCompAnswer(server.getCompiledCode());
                //TODO handle no input from user
             setNextView();
            }
        });
    }

    private void setNextView(){
        //if(chechAnswer(answer)){
        startActivity(new Intent(WriteCode.this, PassedLevel.class));
              /*   }
                else {
                startActivity(new Intent(FillInTheBlanks.this, FailedLevel.class));
                }

                 */
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

    //Sets the user input to one string
    public void setAnswer(){
        answer = WriteCode.this.userCode.getText().toString();
    }

    //Runs the server
    private void runServer(){
        server.setUserCode(answer);
        server.startRunning();
    }


}
