package tda367.myapplication.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import tda367.myapplication.R;
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
    //private tda367.myapplication.model.WriteCode writeCode;
    private Server server;
    private TextView questionView;
    private LearnJava learnJava = LearnJava.getInstance();
    private String codeResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_code);

        submit = (Button)findViewById(R.id.codeSubmit);
        userCode   = (EditText)findViewById(R.id.codeEditText);
        server = new Server("10.0.2.2");
        System.out.println("Created server");
        System.out.println("Server compiled: " + server.getCompiledCode());
        //writeCode = (tda367.myapplication.model.WriteCode) learnJava.getQuery();
        questionView = (TextView) findViewById(R.id.codeQuestion);

        setQuestionText();

        //Sets the toolbar and enables upnavigation, and sets the title
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarActivities);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Write code");



        //Compiles code and checks if answer is right, sets PassedLevelview if correct, otherwise FailedLevel
        submit.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(WriteCode.this);
                View mView;
                setAnswer();
                System.out.println("Server is about to connect");
                //server = new Server("127.0.0.1");
                //runServer();
                //codeResult = server.getCompiledCode();
                System.out.println("Compiled code: " + server.getCompiledCode());
                //TODO handle no input from user
                if(answer.isEmpty()) {
                    //todo display a message to the user that there was input missing
                }else {
                    SendfeedbackCode code = new SendfeedbackCode();
                    code.execute();
                    while (!code.isDone()) {
                        //TODO: Fix this :)))
                    }
                    if (learnJava.getLevelModel().checkAnswer(codeResult)) {
                        mView = getLayoutInflater().inflate(R.layout.activity_passed_level, null);

                        mBuilder.setPositiveButton("Nästa nivå", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(WriteCode.this,PlayFragment.class));
                                if(learnJava.getCurrentCategory().equals(4)) {
                                    //TODO Fix congratulations-message --> tutorial is done!
                                }
                            }
                        });

                        mBuilder.setNeutralButton("Tillbaka", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(WriteCode.this, LevelActivity.class));
                            }
                        });

                    /*
                        Button next = (Button) mView.findViewById(R.id.nextButton);
                        Button back = (Button) mView.findViewById(R.id.backButton);

                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(WriteCode.this, LevelActivity.class));
                                if(!learnJava.getCurrentCategory().equals(4)) {
                                    learnJava.setCurrentCategory(learnJava.getCurrentCategory() + 1);
                                } else {
                                    //TODO Fix congratulations-message --> tutorial is done!
                                }
                            }
                        });
                        back.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(WriteCode.this, LevelActivity.class));
                            }
                        });
                     */
                        mBuilder.setView(mView);
                        mBuilder.setCancelable(false);
                    } else {
                        //TODO show "getError-method" on screen
                        mView = getLayoutInflater().inflate(R.layout.activity_failed_level, null);
                        mBuilder.setPositiveButton("Pröva igen", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                      /*  Button tryAgan = (Button) mView.findViewById(R.id.tryAgain);
                        System.out.println("Error: " + getError());

                        tryAgan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                System.out.println("Try again click");
                            }
                        });
                        */
                        mBuilder.setView(mView);
                        mBuilder.setCancelable(false);
                        //startActivity(new Intent(FillInTheBlanks.this, FailedLevel.class));

                    }
                }
                AlertDialog dialog = mBuilder.create();
                dialog.show();
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

    //Sets the user input to one string
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
