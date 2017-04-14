package tda367.myapplication.Controller;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import tda367.myapplication.R;

/**
 * Created by madeleine on 2017-04-14.
 */

public class ToolbarActivity extends AppCompatActivity {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.toolbar_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarActivities);
        setSupportActionBar(toolbar);

        LayoutInflater inflater = LayoutInflater.from(this);
        View contentView = inflater.inflate(layoutResID, null);

        CoordinatorLayout layout = (CoordinatorLayout) findViewById(R.id.layout);
        layout.addView(contentView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }
}
