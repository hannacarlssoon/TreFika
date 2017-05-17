package tda367.myapplication.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import tda367.myapplication.R;
import tda367.myapplication.model.AccountManager;
import tda367.myapplication.model.LearnJava;
import tda367.myapplication.model.LevelModel;
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
    private Server server;
    private TextView questionView;
    private LearnJava learnJava = LearnJava.getInstance();
    private String codeResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_code);

        setView();

        setQuestionText();

        setToolbar();

        setSubmitButton();

    }

    //Sets OnClick-listener for submit button
    private void setSubmitButton() {
        submit.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(WriteCode.this);
                //TODO add text telling the code is compiling
                setAnswer();
                System.out.println("Server is about to connect");
                //server = new Server("127.0.0.1");
                //runServer();
                //codeResult = server.getCompiledCode();
                if(answer.isEmpty()) {
                    showMessage("Input saknas");
                }else {
                    SendfeedbackCode code = new SendfeedbackCode();
                    code.execute();
                    while (!code.isDone()) {
                        //TODO: Fix this :)))
                    }
                    System.out.println("Compiled code: " + server.getCompiledCode());
                    if (learnJava.getLevelModel().checkAnswer(codeResult)) {
                        if (AccountManager.getInstance().getActiveUser() != null) {
                            AccountManager.getInstance().getActiveUser().getUserStatistics().stopTimer();
                            //TODO fix call to save
                            AccountManager.getInstance().getActiveUser().saveStatistics("", false, false);
                        }
                            setPassedLevel(mBuilder);
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
                Toast toast = Toast.makeText(WriteCode.this, "Din kod gav: " + getError(), Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();
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
                startActivity(new Intent(WriteCode.this,PlayFragment.class));
                Toast toast = Toast.makeText(WriteCode.this, "Du har öppnat nästa kategori", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();
                if(learnJava.getCurrentCategory().equals(4)) {
                    Toast toast2 = Toast.makeText(WriteCode.this, "Du är nu färdig med LearnJava, grattis!", Toast.LENGTH_LONG);
                    toast2.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast2.show();
                }
            }
        });

        mBuilder.setNeutralButton("Tillbaka", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(WriteCode.this, LevelActivity.class));
            }
        });

        mBuilder.setView(mView);
        mBuilder.setCancelable(false);
    }

    //Shows message when no input
    private void showMessage(String message) {
        Toast toast = Toast.makeText(WriteCode.this, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 470);
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
        userCode   = (EditText)findViewById(R.id.codeEditText);
        server = new Server("10.0.2.2");
        System.out.println("Created server");
        System.out.println("Server compiled: " + server.getCompiledCode());
        questionView = (TextView) findViewById(R.id.codeQuestion);
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
    //TODO change system.out.print -> print
    public void setAnswer(){
        answer = WriteCode.this.userCode.getText().toString();
    }

    //Runs the server
    private void runServer(){
        System.out.println("User code is set");
        server.setUserCode(answer);
        server.startRunning();
    }

    public void setQuestionText(){
        LevelModel[] levelModels = learnJava.getLevelHashMap().get(learnJava.getCurrentCategory());
        questionView.setText(levelModels[learnJava.getCurrentLevel()].getQuery().getQuestion());
    }

    public String getError(){
        if(codeResult.toLowerCase().equals("error")){
            return "Error";
        }
            return codeResult;
    }

    private class SendfeedbackCode extends AsyncTask<String, Void, String> {
        private boolean isDone = false;

        @Override
        protected String doInBackground(String[] params) {
           try{
                runServer();
                codeResult = server.getCompiledCode();
               System.out.println(codeResult);
                isDone = true;
            return "some message";
           } catch (Exception e) {
               e.printStackTrace();

               return null;
           }
        }

        public boolean isDone() {
            return isDone;
        }



        @Override
        protected void onPostExecute(String message) {
            //process message
        }
    }


}
