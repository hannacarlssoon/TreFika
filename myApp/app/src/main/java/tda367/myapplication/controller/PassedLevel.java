package tda367.myapplication.controller;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import tda367.myapplication.R;
import tda367.myapplication.model.*;

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

    PassedLevel(final Activity activity) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(activity);

        View mView = activity.getLayoutInflater().inflate(R.layout.activity_passed_level, null);
        starOne = (ImageView) mView.findViewById(R.id.starOne);
        starTwo = (ImageView) mView.findViewById(R.id.starTwo);
        starThree = (ImageView) mView.findViewById(R.id.starThree);
        setStars();

        if(learnJava.getCurrentLevel() == 4){
            mBuilder.setPositiveButton("Nästa nivå", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (learnJava.getCurrentCategory() == 4) {
                        activity.startActivity(new Intent(activity, LevelActivity.class));
                        Toast toast2 = Toast.makeText(activity, "Du är nu färdig med LearnJava, grattis!", Toast.LENGTH_LONG);
                        toast2.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        toast2.show();

                    } else {
                        activity.startActivity(new Intent(activity, LearnJava.class));
                        learnJava.setCurrentCategory(learnJava.getCurrentCategory() + 1);
                        learnJava.setCurrentLevel(0);
                        Toast toast = Toast.makeText(activity, "Du har öppnat nästa kategori", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        toast.show();
                    }
                }
             });

        }else{

            mBuilder.setPositiveButton("Nästa nivå", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    learnJava.setCurrentLevel(learnJava.getCurrentLevel() + 1);
                    activity.startActivity(new Intent(activity, ActivityInfo.class));

                }
            });
         }


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

    public void setStars(){int level = learnJava.getCurrentLevel() + 1;
        if(!statistics.getStatisticsHint().get(statistics.findIndex("category" + learnJava.getCurrentCategory() +  level))){
            starThree.setImageResource(ic_star_black_24dp);
        }
        else{
            starThree.setImageResource(ic_star_border_black_24dp);
        }
        //!statistics.getHintHashMap().get(learnJava.getCurrentCategory()).get(learnJava.getCurrentLevel())
        if(!statistics.getStatisticsKey().get(statistics.findIndex("category" + learnJava.getCurrentCategory() + level))){
            starTwo.setImageResource(ic_star_black_24dp);
        }
        else{
            starTwo.setImageResource(ic_star_border_black_24dp);
        }
        starOne.setImageResource(ic_star_black_24dp);
    }

}
