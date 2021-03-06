package tda367.myapplication.controller;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import tda367.myapplication.R;
import tda367.myapplication.model.*;

import static tda367.myapplication.R.drawable.ic_star_black_24dp;
import static tda367.myapplication.R.drawable.ic_star_border_black_24dp;

/**
 * @author Madeleine Lexén, revised by Sara Kitzing
 * This class is responsible for handling the dialog shown when the user passes a level.
 * Uses LevelModel, AccountManager, User, Statistics, LevelActivity, activity_passed_level.xml
 * Used by QuestionMultiChoice, FillInTheBlanks, WriteCode
 */

public class PassedLevel {

    private ImageView starOne;
    private ImageView starTwo;
    private ImageView starThree;
    private Statistics statistics = AccountManager.getInstance().getActiveUser().getUserStatistics();
    private LevelModel levelModel = LevelModel.getInstance();

    PassedLevel(final Activity activity) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(activity);

        View mView = activity.getLayoutInflater().inflate(R.layout.activity_passed_level, null);
        starOne = (ImageView) mView.findViewById(R.id.starOne);
        starTwo = (ImageView) mView.findViewById(R.id.starTwo);
        starThree = (ImageView) mView.findViewById(R.id.starThree);
        setStars();

        setPositivDialogButton(activity, mBuilder);

        //sets the negativ button and an clickListener that takes user up to levelActivity
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

    //sets the positiv dialogButton and an clickListener that handles user click
    private void setPositivDialogButton(final Activity activity, AlertDialog.Builder mBuilder) {
        if(levelModel.getCurrentLevel() == 4){
            mBuilder.setPositiveButton("Nästa nivå", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (levelModel.getCurrentCategory() == 4) {
                        activity.startActivity(new Intent(activity, LevelActivity.class));
                        Toast toast2 = Toast.makeText(activity, "Du är nu färdig med Learn Java, grattis!", Toast.LENGTH_LONG);
                        toast2.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        toast2.show();

                    } else {
                        activity.startActivity(new Intent(activity, LevelActivity.class));
                        levelModel.setCurrentCategory(levelModel.getCurrentCategory() + 1);
                        levelModel.setCurrentLevel(0);
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
                    levelModel.setCurrentLevel(levelModel.getCurrentLevel() + 1);
                    activity.startActivity(new Intent(activity, ActivityInfo.class));

                }
            });
         }
    }


    //checks the statistics and sets the stars based on htat
    private void setStars(){int level = levelModel.getCurrentLevel() + 1;
        if(!statistics.getStatisticsHint().get(statistics.findIndex("category" + levelModel.getCurrentCategory() +  level))){
            starThree.setImageResource(ic_star_black_24dp);
        }
        else{
            starThree.setImageResource(ic_star_border_black_24dp);
        }
        if(!statistics.getStatisticsKey().get(statistics.findIndex("category" + levelModel.getCurrentCategory() + level))){
            starTwo.setImageResource(ic_star_black_24dp);
        }
        else{
            starTwo.setImageResource(ic_star_border_black_24dp);
        }
        starOne.setImageResource(ic_star_black_24dp);
    }

}
