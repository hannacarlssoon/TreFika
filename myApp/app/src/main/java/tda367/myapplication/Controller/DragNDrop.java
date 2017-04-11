package tda367.myapplication.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import tda367.myapplication.Model.CodeSegments;
import tda367.myapplication.R;

public class DragNDrop extends AppCompatActivity {

    private ListView listView;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_ndrop);
        btn = (Button) findViewById(R.id.dragSubmit);
        listView = (ListView) findViewById(R.id.list);

        // Defined Array values to show in ListView
        ArrayList<String> values = new ArrayList<String>();
        for (int i = 0; i < CodeSegments.rows.length; ++i) {
            values.add(CodeSegments.rows[i]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_drag_ndrop, R.id.dragTextView, values);

        // Assign adapter to ListView
        listView.setAdapter(adapter);

        //sets listener on submitbutton, checks if answer is correct,
        // changes view to passedLevel if correct, otherwise to FailedLevel.
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setAnswer();
                // if(checkAnswer()){
                startActivity(new Intent(DragNDrop.this, PassedLevel.class));
            /* }
            else{
            startActivity(new Intent(QuestionMultiChoice.this, FailedLevel.class));
            }
             */
            }
        });

        /*
        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);
            }
        });

        listView.setAdapter(adapter);
    }


    //Gets the órder of the elements in the listview, and sets that as answer
    protected void setAnswer(){
        //TODO get the order of the elements in the listview and set answer to that.
        //System.out.println(answer);
    }

*/

    }
}
