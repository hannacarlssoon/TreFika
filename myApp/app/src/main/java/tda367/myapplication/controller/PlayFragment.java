package tda367.myapplication.controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import tda367.myapplication.model.LearnJava;
import tda367.myapplication.HashMapCreator;
import tda367.myapplication.R;


/**
 * Handles the setting of the views when the main category buttons are clicked
 * A simple {@link Fragment} subclass.
 * @author HannaCarlsoon. Revised by Madeleine Lexén and Tobias Lindgren.
 */
public class PlayFragment extends Fragment implements View.OnClickListener {

    public LearnJava learnJava = LearnJava.getInstance();

    public PlayFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_play, container, false);

        //Creates an instance of HashMapCreator and passes it to learnJava when it's initiated
        HashMapCreator hcreate = new HashMapCreator(getContext());
        learnJava.init(hcreate.getHashMap());

        //Sets id:s to the buttons
        Button b1 = (Button) view.findViewById(R.id.category1);
        Button b2 = (Button) view.findViewById(R.id.category2);
        Button b3 = (Button) view.findViewById(R.id.category3);

        //Sets onCLickListeners to the buttons
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);

        return view;
    }

    //Overridden method from onClickListners, sets the view when a button is clicked
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.category1:
                Intent intent1 = new Intent(getActivity(), LevelActivity.class);
                learnJava.setCurrentCategory("category1");
                startActivity(intent1);
                break;
            case R.id.category2:
                Intent intent2 = new Intent(getActivity(), LevelActivity.class);
                learnJava.setCurrentCategory("category2");
                startActivity(intent2);
                break;
            case R.id.category3:
                Intent intent3 = new Intent(getActivity(), LevelActivity.class);
                learnJava.setCurrentCategory("category3");
                startActivity(intent3);
                break;
        }
    }
}
