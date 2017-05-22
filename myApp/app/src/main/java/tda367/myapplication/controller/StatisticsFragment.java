package tda367.myapplication.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import tda367.myapplication.R;
import tda367.myapplication.model.AccountManager;
import tda367.myapplication.model.Statistics;


public class StatisticsFragment extends Fragment {

    private Statistics statistics = AccountManager.getInstance().getActiveUser().getUserStatistics();

    public StatisticsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_statistics, container, false);

        ListView listView = (ListView) view.findViewById(R.id.listView);
        CustomAdapter customAdapter = new CustomAdapter(getActivity(), statistics.getStatisticsTime(),
                statistics.getStatisticsKey(), statistics.getStatisticsHint());


        listView.setAdapter(customAdapter);

        return view;
    }


}
