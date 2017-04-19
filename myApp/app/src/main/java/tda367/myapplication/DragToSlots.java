package tda367.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//import tda367.myapplication.Controller.DragNDrop;
import tda367.myapplication.Controller.PassedLevel;

public class DragToSlots extends AppCompatActivity {
    Button btn;
    TextView codeSegOne;
    TextView codeSegTwo;
    TextView codeSegThree;
    TextView slotOne;
    TextView slotTwo;
    TextView slotThree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_to_slots);
        btn = (Button) findViewById(R.id.submitDragSlots);
        codeSegOne = (TextView) findViewById(R.id.codeSegOne);
        codeSegTwo = (TextView) findViewById(R.id.codeSegTwo);
        codeSegThree = (TextView) findViewById(R.id.codeSegThree);
        slotOne = (TextView) findViewById(R.id.slotOne);
        slotTwo = (TextView) findViewById(R.id.slotTwo);
        slotThree = (TextView) findViewById(R.id.slotThree);


        codeSegOne.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                codeSegOne.setText("I am Being Clicked Long");
                return false;
            }
        });

        codeSegTwo.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                codeSegOne.setText("I am Being Clicked Long");
                return false;
            }
        });

        codeSegThree.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                codeSegOne.setText("I am Being Clicked Long");
                return false;
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DragToSlots.this, PassedLevel.class);
                intent.putExtra("ARG_QUESTION", "strifdnd");
                startActivity(intent);
            }
        });
    }
}
