package tda367.myapplication.controller;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import tda367.myapplication.R;
import tda367.myapplication.model.AccountManager;
import tda367.myapplication.model.LearnJava;

/**
 * Created by hannacarlsson on 2017-05-15.
 */

public class CustomAdapter extends BaseAdapter {

    private Activity activity;
    private List<Long> timeList;
    private List<Boolean> keyList;
    private List<Boolean> hintList;

    public CustomAdapter(Activity activity, List<Long> timeList,
                         List<Boolean> keyList, List<Boolean> hintList) {
        this.activity = activity;
        this.timeList = timeList;
        this.keyList = keyList;
        this.hintList = hintList;
    }


    @Override
    public int getCount() {
        return timeList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = activity.getLayoutInflater().inflate(R.layout.custom_layout, null);

        TextView category = (TextView) view.findViewById(R.id.category_name);
        TextView level = (TextView) view.findViewById(R.id.level_name);
        TextView hint = (TextView) view.findViewById(R.id.hint);
        TextView time = (TextView) view.findViewById(R.id.time);
        TextView key = (TextView) view.findViewById(R.id.key);

        if (hintList.get(i)) {
            hint.setText("Ja");
        } else {
            hint.setText("Nej");
        }
        if (keyList.get(i)) {
            key.setText("Ja");
        } else {
            key.setText("Nej");
        }

        time.setText(timeList.get(i).toString());

        return view;
    }
}
