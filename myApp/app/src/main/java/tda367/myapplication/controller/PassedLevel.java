package tda367.myapplication.controller;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import tda367.myapplication.R;
import tda367.myapplication.model.AccountManager;
import tda367.myapplication.model.LearnJava;
import tda367.myapplication.model.Statistics;

import static android.R.drawable.btn_star_big_on;

/**
 * Created by madeleine on 2017-05-17.
 */

public class PassedLevel extends AppCompatActivity {

    private ImageView starTwo;
    private ImageView starThree;
    Context context;
    private Statistics statistics = AccountManager.getInstance().getActiveUser().getUserStatistics();
    private LearnJava learnJava = LearnJava.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passed_level);
        context = this;
        starTwo = (ImageView) findViewById(R.id.starTwo);
        starThree = (ImageView) findViewById(R.id.starThree);
        setStars();
    }


    public void setStars(){
        if(!statistics.getKeyHashMap().get(learnJava.getCurrentCategory()).get(learnJava.getCurrentLevel())){
            starTwo.setImageDrawable();
        }
    }

}
