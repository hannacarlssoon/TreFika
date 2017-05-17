package tda367.myapplication.controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tda367.myapplication.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadMoreFragment extends Fragment {
    private TextView textTyp;
    private TextView textIf;
    private TextView textFor;
    private TextView textArr;
    private String typ = "Något om typer här";
    private String ifbo = "Något om if/boolean här";
    private String forwhi = "Något om for/while här";
    private String arr = "Något om arrayer här";



    public ReadMoreFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read_more, container, false);
        textTyp = (TextView) view.findViewById(R.id.textTyp);
        textIf = (TextView) view.findViewById(R.id.textIf);
        textFor = (TextView) view.findViewById(R.id.textFor);
        textArr = (TextView) view.findViewById(R.id.textArr);

        textTyp.setText(typ);
        textIf.setText(ifbo);
        textFor.setText(forwhi);
        textArr.setText(arr);

        // Inflate the layout for this fragment
        return view;
    }

}
