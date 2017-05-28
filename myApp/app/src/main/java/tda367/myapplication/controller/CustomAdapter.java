package tda367.myapplication.controller;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import tda367.myapplication.R;

/**
 * @author Hanna Carlsson
 * Responsibility: Makes the custom listviews to display the statistics
 * Uses: ViewHolder, custom_layout.xml
 * Used by: StatisticsFragment
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

    //Gets the specific custom view for each compontent in the statisticsFragment listview
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        //Uses pattern ViewHolder to make more efficient listview
        ViewHolder holder = null;
        if (view == null) {
            view = activity.getLayoutInflater().inflate(R.layout.custom_layout, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        setValues(i, holder);

        setNames(i, holder);

        return view;
    }

    //Sets the level and category names
    private void setNames(int i, ViewHolder holder) {
        holder.getTime().setText(timeList.get(i).toString() + " s");
        int currLevel = i % 5 + 1;
        holder.getLevel().setText("Nivå " + currLevel);
        for (int j = 0; j < i + 1; j++) {
            if (j < 5) {
                holder.getCategory().setText("Kategori 1");
            } else if (j < 10) {
                holder.getCategory().setText("Kategori 2");
            } else if (j < 15) {
                holder.getCategory().setText("Kategori 3");
            } else if (j < 20) {
                holder.getCategory().setText("Kategori 4");
            }
        }
    }

    //Sets the boolean values, true -> Ja and false -> Nej
    private void setValues(int i, ViewHolder holder) {
        if (hintList.get(i)) {
            holder.getHint().setText("Ja");
        } else {
            holder.getHint().setText("Nej");
        }
        if (keyList.get(i)) {
            holder.getKey().setText("Ja");
        } else {
            holder.getKey().setText("Nej");
        }
    }
}
