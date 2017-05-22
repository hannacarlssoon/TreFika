package tda367.myapplication.controller;

import android.content.Context;
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
import android.widget.ImageButton;
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

        hintButton.setVisibility(View.INVISIBLE);

        hintButton.setOnClickListener(hintListener);

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
                if(answer.isEmpty()) {
                    showMessage("Input saknas");
                }else {
                    SendfeedbackCode code = new SendfeedbackCode();
                    code.execute();
                    while (!code.isDone()) {
                        //TODO: Fix this :)))
                    }
                    if (learnJava.getLevelModel().checkAnswer(codeResult)) {
                        try {
                            AccountManager.getInstance().getActiveUser().getUserStatistics().stopTimer();
                            int level = LearnJava.getInstance().getCurrentLevel() + 1;
                            AccountManager.getInstance().getActiveUser().saveStatistics(LearnJava.getInstance().getCurrentCategory() + level, keyUsed, showKey);
                            LevelActivity lv = new LevelActivity();
                            lv.enablePassedLevels();
                        } catch (NullPointerException e) {
                        } finally {
                            new PassedLevel(WriteCode.this);
                        }
                    } else {
                        setFailedLevel(mBuilder);
                        hintButton.setVisibility(View.VISIBLE);
                        AlertDialog dialog = mBuilder.create();
                        dialog.show();
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
                Toast toast = Toast.makeText(WriteCode.this, "Din kod gav: " + getError(), Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();
            }
        });
        mBuilder.setView(mView);
        mBuilder.setCancelable(false);
    }

    /*
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
    */

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
        hintButton = (ImageButton) findViewById(R.id.hintButton);
        userCode   = (EditText)findViewById(R.id.codeEditText);
        server = new Server("10.0.2.2");
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
        answer = answer.toLowerCase().replace("system.out.println", "print");
        answer = answer.toLowerCase().replace("system.out.print", "print");
    }

    //Runs the server
    private void runServer(){
        server.setUserCode(answer);
        server.startRunning();
    }

    public void setQuestionText(){
        LevelModel[] levelModels = learnJava.getLevelHashMap().get(learnJava.getCurrentCategory());
        questionView.setText(levelModels[learnJava.getCurrentLevel()].getQuery().getQuestion());
    }

    public String getError(){
        if(codeResult.equalsIgnoreCase("error")){
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
