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
        listView = (ListView) findViewById(R.id.list);
        btn = (Button) findViewById(R.id.dragSubmit);

        // Defined Array values to show in ListView
        ArrayList<String> values = new ArrayList<String>();
        for (int i = 0; i < CodeSegments.rows.length; ++i) {
            values.add(CodeSegments.rows[i]);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_drag_ndrop, R.id.dragTextView, values);

        // Assign adapter to ListView
        // listView.setAdapter(adapter);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DragNDrop.this, PassedLevel.class);
                intent.putExtra("ARG_QUESTION", "strifdnd");
                startActivity(intent);
            }
        });

    }
}



