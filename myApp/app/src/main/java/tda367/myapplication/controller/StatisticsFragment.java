package tda367.myapplication.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tda367.myapplication.R;
import tda367.myapplication.model.AccountManager;


public class StatisticsFragment extends Fragment {

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHashMap;


    public StatisticsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_statistics, container, false);

        listView = (ExpandableListView) view.findViewById(R.id.lvExp);
        initData();
        listAdapter = new ExpandableListAdapter(getContext(), listDataHeader, listHashMap);
        listView.setAdapter(listAdapter);

        return view;
    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHashMap = new HashMap<>();

        listDataHeader.add("Typer och variabler");
        listDataHeader.add("If satser och boolean logik");
        listDataHeader.add("For och while loopar");
        listDataHeader.add("Arrayer");

        List<String> one = new ArrayList<>();

        for (int i = 0; i < AccountManager.getInstance().
                getActiveUser().getUserStatistics().getStatisticsHint().size(); i++) {

        }

        AccountManager.getInstance().getActiveUser().getUserStatistics().

        List<String> two = new ArrayList<>();
        two.add("a");
        two.add("b");
        two.add("c");
        two.add("d");

        List<String> three = new ArrayList<>();
        three.add("q");
        three.add("w");
        three.add("e");
        three.add("r");

        List<String> four = new ArrayList<>();
        four.add("a1");
        four.add("b2");
        four.add("c3");
        four.add("d4");

        listHashMap.put(listDataHeader.get(0), one);
        listHashMap.put(listDataHeader.get(1), two);
        listHashMap.put(listDataHeader.get(2), three);
        listHashMap.put(listDataHeader.get(3), four);
    }


}
