package tda367.myapplication.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import tda367.myapplication.R;
import tda367.myapplication.model.AccountManager;
import tda367.myapplication.model.Statistics;

/**
 * @author Hanna Carlsson
 * Responsibility: Sets the view of the statistics fragment
 * Uses: CustomAdapter, AccountManager, User, Statistics, fragment_statistics.xml
 * Used by: MainActivity, FillInTheBlanks
 */

public class StatisticsFragment extends Fragment {

    public StatisticsFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_statistics, container, false);

        //Sets id to listview
        ListView listView = (ListView) view.findViewById(R.id.listView);

        if (AccountManager.getInstance().getActiveUser() != null) {
            Statistics statistics = AccountManager.getInstance().getActiveUser().getUserStatistics();

            //Makes CustomAdapter object
            CustomAdapter customAdapter = new CustomAdapter(getActivity(), statistics.getStatisticsTime(),
                    statistics.getStatisticsKey(), statistics.getStatisticsHint());

            //Sets the listviews adapter to the customAdapter
            listView.setAdapter(customAdapter);
        } else {
            Toast.makeText(getContext(), "Logga in om du vill se din statistik", Toast.LENGTH_SHORT).show();
        }

        return view;
    }


}
