package tda367.myapplication.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.HashMap;
import java.util.List;

import tda367.myapplication.R;


public class StatisticsFragment extends Fragment {

    HashMap<String, List<String>> categories;
    List<String> levels;
    ExpandableListView expandableListView;


    public StatisticsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_statistics, container, false);

        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableList);

        return view;
    }


}
