package tda367.myapplication.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import tda367.myapplication.R;
import tda367.myapplication.model.AccountManager;
import tda367.myapplication.model.LearnJava;
import tda367.myapplication.model.LevelModel;
import tda367.myapplication.service.Server;

/**
 * @author Sara Kitzing, revised by Madeleine Lexén and Tobias Lindgren
 * Responsible for handling the events from the WriteCode questions view. Creates and
 * calls the server to compile the users code
 * Used by ActivityInfo
 * Uses LearnJava, activity_write_code.xml, PassedLevel, Server, User, Statistics
 */


public class WriteCode extends AppCompatActivity {
    Button submit;
    private ImageButton hintButton;
    EditText userCode;
    private String answer;
    private Server server;
    private TextView questionView;
    private LearnJava learnJava = LearnJava.getInstance();
    private String codeResult;
    private boolean showKey = false;
    private Context context;
    private boolean keyUsed = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_code);
        context = this;

        setView();
        setQuestionText();
        setToolbar();
        setSubmitButton();

        //Makes the hint button invisible until one wrong answer
        hintButton.setVisibility(View.INVISIBLE);

        //sets onclicklistener for hint button
        hintButton.setOnClickListener(hintListener);

    }

    //Sets OnClick-listener for submit button
    private void setSubmitButton() {
        submit.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(WriteCode.this);
                showMessage("Din kod kompileras", Toast.LENGTH_SHORT, 470);
                setAnswer();
                if(answer.isEmpty()) {
                    showMessage("Input saknas", Toast.LENGTH_SHORT, 470);
                }else {
                    try {
                        SendfeedbackCode code = new SendfeedbackCode();
                        code.execute().get();
                    }catch(ExecutionException e){
                        showMessage("Något störde exekveringen av koden, försök igen senare.", Toast.LENGTH_LONG, 0);
                    }catch(InterruptedException e){
                        showMessage("Något störde exekveringen av koden, försök igen senare.", Toast.LENGTH_LONG, 0);
                    }
                    if (learnJava.getLevelModel().checkAnswer(codeResult)) {
                        try {
                            AccountManager.getInstance().getActiveUser().getUserStatistics().stopTimer();
                            int level = LearnJava.getInstance().getCurrentLevel() + 1;
                            AccountManager.getInstance().getActiveUser().saveStatistics("category" + LearnJava.getInstance().getCurrentCategory() + level, keyUsed, showKey);
                            LevelActivity lv = new LevelActivity();
                            lv.enablePassedLevels();
                        } catch (NullPointerException e) {
                        } finally {
                            new PassedLevel(WriteCode.this);
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
                showMessage("Din kod gav: " + getError(), Toast.LENGTH_LONG, 0);
            }
        });
        mBuilder.setView(mView);
        mBuilder.setCancelable(false);
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }

    //Shows toast-message
    private void showMessage(String message, int length, int x) {
        Toast toast = Toast.makeText(WriteCode.this, message, length);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, x);
        toast.show();
    }

    //Sets the toolbar and enables upnavigation, and sets the title
    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarActivities);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Write code");
    }

    //Sets buttons and views
    private void setView() {
        submit = (Button)findViewById(R.id.codeSubmit);
        hintButton = (ImageButton) findViewById(R.id.hintButton);
        userCode   = (EditText)findViewById(R.id.codeEditText);
        server = new Server("10.0.2.2");
        questionView = (TextView) findViewById(R.id.codeQuestion);
        questionView.setMovementMethod(new ScrollingMovementMethod());
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
        answer = answer.toLowerCase().replace("system.out.println", "print");
        answer = answer.toLowerCase().replace("system.out.print", "print");
    }

    //Runs the server
    private void runServer(){
        server.setUserCode(answer);
        server.startRunning();
    }

    //Method for setting the right question to the textView
    public void setQuestionText(){
        LevelModel[] levelModels = learnJava.getLevelHashMap().get("category" + learnJava.getCurrentCategory());
        questionView.setText(levelModels[learnJava.getCurrentLevel()].getQuery().getQuestion());
    }

    //Returns "Error" if error in the executed code, else it returns the result
    public String getError(){
        if(codeResult.equalsIgnoreCase("error")){
            return "Error";
        }
            return codeResult;
    }

    //Thread in which the server is run and the code is compiled
    private class SendfeedbackCode extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String[] params) {
           try{
                runServer();
                codeResult = server.getCompiledCode();
            return "some message";
           } catch (Exception e) {
               showMessage("Det blev tyvärr ett problem med exekverandet av koden, försök igen senare.", Toast.LENGTH_LONG, 0);

               return null;
           }
        }

        @Override
        protected void onPostExecute(String message) {
            //process message
        }
    }

    //Defines onclicklistener for hint button
    private ImageButton.OnClickListener hintListener = new View.OnClickListener() {
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

    //Method for creating dialog and displaying hint
    private void createDialog(String hint) {
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
