package tda367.myapplication.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tda367.myapplication.R;

/**
 * @author  Tobias Lindgren
 * Responsibility: Setting the views in the "About the app"-fragment
 * Used by: MainActivity
 */

public class AboutAppenFragment extends Fragment {

    View view;
    TextView textView;

    public AboutAppenFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_about_appen, container, false);
        // Inflate the layout for this fragment
        textView = (TextView)view.findViewById(R.id.aboutText);
        textView.setText("Created by: Hanna Carlsson, Sara Kitzing, Madeleine Lexen and Tobias Lindgren" + "\n" + "\n" + "Version: 1.0.0");
        return view;
    }

}
