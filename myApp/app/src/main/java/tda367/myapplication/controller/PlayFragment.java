package tda367.myapplication.controller;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


import tda367.myapplication.HashMapCreator;
import tda367.myapplication.model.AccountManager;
import tda367.myapplication.model.LearnJava;
import tda367.myapplication.model.LearnJava;
import tda367.myapplication.R;
import tda367.myapplication.model.Statistics;


/**
 * Handles the setting of the views when the main category buttons are clicked
 * A simple {@link Fragment} subclass.
 * @author HannaCarlsoon. Revised by Madeleine Lexén and Tobias Lindgren.
 */
public class PlayFragment extends Fragment implements View.OnClickListener {

    public LearnJava learnJava = LearnJava.getInstance();
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private boolean cat1IsEnabled = false;
    private boolean cat2IsEnabled = false;
    private boolean cat3IsEnabled = false;
    private boolean cat4IsEnabled = false;



    public PlayFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_play, container, false);

        //Creates an instance of HashMapCreator and passes it to learnJava when it's initiated
        HashMapCreator hcreate = new HashMapCreator(getContext());
        learnJava.init(hcreate.getHashMap());


        //Sets id:s to the buttons
        b1 = (Button) view.findViewById(R.id.category1);
        b2 = (Button) view.findViewById(R.id.category2);
        b3 = (Button) view.findViewById(R.id.category3);
        b4 = (Button) view.findViewById(R.id.category4);

        //Sets onCLickListeners to the buttons
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);

        enableCategories();

       /* setEnabledCategories();
        enableCategories();*/

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
            case R.id.category4:
                Intent intent4 = new Intent(getActivity(), LevelActivity.class);
                learnJava.setCurrentCategory("category4");
                startActivity(intent4);
                break;
        }
    }


    private void setEnabledCategories(){
        try {
            AccountManager ac = AccountManager.getInstance();
            Statistics statistics = ac.getActiveUser().getUserStatistics();
            if (ac.getActiveUser() != null) {
                cat1IsEnabled = true;
            }
            try {
                statistics.getHintHashMap().get("category1").get(4);
                cat2IsEnabled = true;
            } catch (IndexOutOfBoundsException e) {
                cat2IsEnabled = false;
            }
            try {
                statistics.getHintHashMap().get("category2").get(4);
                cat3IsEnabled = true;
            } catch (IndexOutOfBoundsException e) {
                cat3IsEnabled = false;
            }
            try {
                statistics.getHintHashMap().get("category3").get(4);
                cat4IsEnabled = true;
            } catch (IndexOutOfBoundsException e) {
                cat4IsEnabled = false;
            }
        }
        catch (NullPointerException e){
            cat1IsEnabled = false;
            cat2IsEnabled = false;
            cat3IsEnabled = false;
            cat4IsEnabled = false;
        }
    }


    private void enableCategories() {
        setEnabledCategories();
        b1.setEnabled(cat1IsEnabled);
        b2.setEnabled(cat2IsEnabled);
        b3.setEnabled(cat3IsEnabled);
        b4.setEnabled(cat4IsEnabled);

    }
}

