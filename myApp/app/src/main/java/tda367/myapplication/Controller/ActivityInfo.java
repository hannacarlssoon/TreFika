package tda367.myapplication.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tda367.myapplication.Model.LevelModel;
import tda367.myapplication.R;

public class ActivityInfo extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        //getActionBar().setDisplayHomeAsUpEnabled(true);


        Button btn = (Button)findViewById(R.id.button3);
        textView = (TextView)findViewById(R.id.infoText);

        setInfoText();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityInfo.this, QuestionMultiChoice.class);
                intent.putExtra("ARG_QUESTION", "strifdnd");
                startActivity(intent);
            }
        });

    }

    public void setInfoText(){
        LevelModel lm = LevelActivity.getItemFromList(0);
        String infoText = lm.getInfo();
        textView.setText(infoText);
    }


}
