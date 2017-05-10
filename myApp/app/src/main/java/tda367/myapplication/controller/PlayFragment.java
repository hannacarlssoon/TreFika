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
 * A simple {@link Fragment} subclass.
 * @author HannaCarlsoon. Revised by Madeleine Lexén and Tobias Lindgren.
 */
public class PlayFragment extends Fragment implements View.OnClickListener {
    public LearnJava learnJava = LearnJava.getInstance();



    public PlayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Intent intent = new Intent(getActivity(), LevelActivity.class);
        //startActivity(intent);

        View view = inflater.inflate(R.layout.fragment_play, container, false);

        HashMapCreator hcreate = new HashMapCreator(getContext());
        learnJava.init(hcreate.getHashMap());

        Button b1 = (Button) view.findViewById(R.id.category1);
        Button b2 = (Button) view.findViewById(R.id.category2);
        Button b3 = (Button) view.findViewById(R.id.category3);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.category1:
                Intent intent1 = new Intent(getActivity(), LevelActivity.class);
                startActivity(intent1);
                learnJava.setCurrentCategory("category1");
                System.out.println("Hola");
                break;
            case R.id.category2:
                Intent intent2 = new Intent(getActivity(), LevelActivity.class);
                startActivity(intent2);
                learnJava.setCurrentCategory("category2");
                break;
            case R.id.category3:
                Intent intent3 = new Intent(getActivity(), LevelActivity.class);
                startActivity(intent3);
                learnJava.setCurrentCategory("category3");
                break;
        }
    }
}
