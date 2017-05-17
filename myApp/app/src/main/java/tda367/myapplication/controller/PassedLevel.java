package tda367.myapplication.controller;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.List;

import tda367.myapplication.R;
import tda367.myapplication.model.AccountManager;
import tda367.myapplication.model.LearnJava;
import tda367.myapplication.model.Statistics;

import static android.R.drawable.btn_star_big_on;
import static tda367.myapplication.R.drawable.ic_lock_black_24dp;
import static tda367.myapplication.R.drawable.ic_star_black_24dp;
import static tda367.myapplication.R.drawable.ic_star_border_black_24dp;

/**
 * Created by madeleine on 2017-05-17.
 */

public class PassedLevel {

    private ImageView starOne;
    private ImageView starTwo;
    private ImageView starThree;
    private Statistics statistics = AccountManager.getInstance().getActiveUser().getUserStatistics();
    private LearnJava learnJava = LearnJava.getInstance();

    PassedLevel(final Activity activity){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(activity);

        View mView = activity.getLayoutInflater().inflate(R.layout.activity_passed_level, null);
        starOne = (ImageView) mView.findViewById(R.id.starOne);
        starTwo = (ImageView) mView.findViewById(R.id.starTwo);
        starThree = (ImageView) mView.findViewById(R.id.starThree);
        setStars();

        mBuilder.setPositiveButton("Nästa nivå", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                learnJava.setCurrentLevel(learnJava.getCurrentLevel() + 1);
                activity.startActivity(new Intent(activity, ActivityInfo.class));

            }
        });


        mBuilder.setNeutralButton("Tillbaka", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.startActivity(new Intent(activity, LevelActivity.class));
            }
        });
        mBuilder.setView(mView);
        mBuilder.setCancelable(false);


        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }

    public void setStars(){
        System.out.println("SetStars körs");
        int i = learnJava.getCurrentLevel();
        String s = learnJava.getCurrentCategory();
        HashMap<String , List<Boolean>> h = statistics.getKeyHashMap();
        List<Boolean> l = h.get(s);
        boolean b = l.get(i);

        if(!statistics.getKeyHashMap().get(learnJava.getCurrentCategory()).get(learnJava.getCurrentLevel())){
            starThree.setImageResource(ic_star_black_24dp);
            System.out.println("star three sätts");
        }
        else{
            starThree.setImageResource(ic_star_border_black_24dp);
            System.out.println("starTree sätts inte");
        }

        if(!statistics.getHintHashMap().get(learnJava.getCurrentCategory()).get(learnJava.getCurrentLevel())){
            starTwo.setImageResource(ic_star_black_24dp);
            System.out.println("starTwo sätts");
        }
        else{
            starTwo.setImageResource(ic_star_border_black_24dp);
            System.out.println("starTwo sätts inte");
        }
        starOne.setImageResource(ic_star_black_24dp);
        System.out.println("StarOne sätts");
    }

}
