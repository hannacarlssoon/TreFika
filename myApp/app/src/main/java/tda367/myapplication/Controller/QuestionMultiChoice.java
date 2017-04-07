package tda367.myapplication.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import tda367.myapplication.R;

public class QuestionMultiChoice extends AppCompatActivity {

    private RadioGroup radioAnswerGroup;
    private RadioButton radioAnswerButton;
    private Button submitButton;
    private String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_multi_choice);
        radioAnswerGroup = (RadioGroup)findViewById(R.id.radioGroup);
        Button btn = (Button)findViewById(R.id.SubmitButton);

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

    protected void setAnswer(){
        int selectedId = radioAnswerGroup.getCheckedRadioButtonId();
        radioAnswerButton = (RadioButton)findViewById(selectedId);
        answer = (String) radioAnswerButton.getText();
        System.out.println(answer);
    }




}
