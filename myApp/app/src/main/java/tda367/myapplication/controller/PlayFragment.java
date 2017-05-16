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
import tda367.myapplication.model.AccountManager;
import tda367.myapplication.model.LearnJava;
import tda367.myapplication.HashMapCreator;
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
    private boolean cat2Isenabled = false;
    private boolean cat3IsEnabled = false;
    private boolean cat4IsEnabled = false;
    private Statistics statistics = AccountManager.getInstance().getActiveUser().getUserStatistics();


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
        //b4 = (Button) view.findViewById(R.id.category4);

        //Sets onCLickListeners to the buttons
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        //B4.setOnCliclListener(this);

        enableCategories();

        setEnabledCategories();
        enableCategories();

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
           /* case R.id.category4:
                Intent intent4 = new Intent(getActivity(), LevelActivity.class);
                learnJava.setCurrentCategory("category3");
                startActivity(intent4);
                break;
                */
        }
    }


    private void setEnabledCategories(){
        if(statistics.getStatisticsKey(). != null){

        }


    }


    private void enableCategories() {
        if (!cat2Isenabled) {
            b2.setEnabled(cat2Isenabled);
        } else {
            //  b2.setImageResource(R.drawable.ic_lock_black_24dp);
        }
        if (!cat3IsEnabled) {
            b3.setEnabled(cat3IsEnabled);
        } else {
            // b3.setImageResource(R.drawable.ic_lock_black_24dp);
        }
        /*
        if (cat4IsEnabled){
            //  b4.setEnabled(cat4IsEnabled);
        }
        else{
            //  b4.setImageResource(R.drawable.ic_lock_black_24dp);
            */
    }
}
