package tda367.myapplication.controller;

import android.view.View;
import android.widget.TextView;

import tda367.myapplication.R;

/**
 * @author Hanna Carlsson
 * Responsibility: Improve the efficiency of loading the statistics listview by initializing
 * and finding id only once
 * Used by: CustomAdapter
 */

public class ViewHolder {

    private TextView category;
    private TextView level;
    private TextView key;
    private TextView time;
    private TextView hint;

    public ViewHolder(View view) {
        category = (TextView) view.findViewById(R.id.category_name);
        level = (TextView) view.findViewById(R.id.level_name);
        hint = (TextView) view.findViewById(R.id.hint);
        time = (TextView) view.findViewById(R.id.time);
        key = (TextView) view.findViewById(R.id.key);
    }

    public TextView getCategory() {
        return category;
    }

    public TextView getLevel() {
        return level;
    }

    public TextView getKey() {
        return key;
    }

    public TextView getTime() {
        return time;
    }

    public TextView getHint() {
        return hint;
    }
}
