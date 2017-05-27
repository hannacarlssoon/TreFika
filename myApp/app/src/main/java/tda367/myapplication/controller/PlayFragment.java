package tda367.myapplication.controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import tda367.myapplication.model.AccountManager;
import tda367.myapplication.model.LevelModel;
import tda367.myapplication.R;
import tda367.myapplication.model.Statistics;

/**
 * @author Hanna Carlsoon. Revised by Madeleine Lexén and Tobias Lindgren.
 * Responsibility: Handles the setting of the views when the main category buttons are clicked
 * Uses: LevelModel, HashMapCreator, LevelActivity, AccountManager, User, MyPageFragment, fragment_play.xml
 * Used by: SignInFragment, MainActivity
 */
public class PlayFragment extends Fragment implements View.OnClickListener {

    public LevelModel levelModel = LevelModel.getInstance();
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private boolean[] enabledCategories;

    public PlayFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_play, container, false);

        createHashMapCreator();

        setId(view);

        setOnClickListners();

        enableCategories();

        return view;
    }

    //Creates an instance of HashMapCreator and passes it to levelModel when it's initiated
    private void createHashMapCreator() {
        HashMapCreator hcreate = new HashMapCreator(getContext());
        levelModel.init(hcreate.getHashMap());
        enabledCategories = new boolean[levelModel.getAmountOfCategories() + 1];
    }

    //Sets onCLickListeners to the buttons
    private void setOnClickListners() {
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
    }

    //Sets id:s to the buttons
    private void setId(View view) {
        b1 = (Button) view.findViewById(R.id.category1);
        b2 = (Button) view.findViewById(R.id.category2);
        b3 = (Button) view.findViewById(R.id.category3);
        b4 = (Button) view.findViewById(R.id.category4);
    }

    //Overridden method from onClickListners, sets the view when a button is clicked
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.category1:
                Intent intent1 = new Intent(getActivity(), LevelActivity.class);
                levelModel.setCurrentCategory(1);
                startActivity(intent1);
                break;
            case R.id.category2:
                Intent intent2 = new Intent(getActivity(), LevelActivity.class);
                levelModel.setCurrentCategory(2);
                startActivity(intent2);
                break;
            case R.id.category3:
                Intent intent3 = new Intent(getActivity(), LevelActivity.class);
                levelModel.setCurrentCategory(2);
                startActivity(intent3);
                break;
            case R.id.category4:
                Intent intent4 = new Intent(getActivity(), LevelActivity.class);
                levelModel.setCurrentCategory(4);
                startActivity(intent4);
                break;
        }
    }

    //TODO Lexi kommentera
    private void setEnabledCategories(){
        AccountManager ac = AccountManager.getInstance();
        Statistics statistics = ac.getActiveUser().getUserStatistics();
        if (ac.getActiveUser() != null) {
            enabledCategories[1] = true;

            for (int i = 1; i < levelModel.getAmountOfCategories(); i++) {
                try {
                    statistics.getStatisticsHint().get(statistics.findIndex("category" + i + "5"));
                    enabledCategories[i + 1] = true;
                } catch (IndexOutOfBoundsException e) {
                        enabledCategories[i + 1] = false;
                }
            }
        }
        else{
            for(int i = 1; i < levelModel.getAmountOfCategories() + 1; i++){
                enabledCategories[i]  = false;
            }
        }
    }

    //Enables the different categories
    private void enableCategories() {
        setEnabledCategories();
        b1.setEnabled(enabledCategories[1]);
        b2.setEnabled(enabledCategories[2]);
        b3.setEnabled(enabledCategories[3]);
        b4.setEnabled(enabledCategories[4]);

    }
}

