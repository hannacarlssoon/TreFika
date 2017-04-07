package tda367.myapplication.Controller;

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import tda367.myapplication.R;

public class DragNDrop extends AppCompatActivity {

    private Button lineOne;
    private Button lineTwo;
    private Button lineThree;
    private Button lineFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_ndrop);

        lineOne = (Button) findViewById(R.id.button5);
        lineOne.isClickable();
        lineTwo = (Button) findViewById(R.id.button6);
        lineTwo.isClickable();
        lineThree = (Button) findViewById(R.id.button7);
        lineThree.isClickable();
        lineFour = (Button) findViewById(R.id.button8);
        lineFour.isClickable();


    }

}
