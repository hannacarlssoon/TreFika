package tda367.myapplication.controller;

import android.app.Application;
import android.media.MediaPlayer;

import tda367.myapplication.R;

/**
 * Created by Tobias Lindgren on 2017-05-25.
 * Responsibility: Starts music on opening of app, stops music on terminate
 * Uses:
 * Used by: SettingFragment
 */

public class LearnJavaController extends Application {

    public static MediaPlayer mPlayer;

    @Override
    public void onCreate(){
        super.onCreate();
        setUpMediaPlayer();
    }

    @Override
    public void onTerminate(){
        super.onTerminate();
        mPlayer.stop();
        mPlayer.release();
    }

    private void setUpMediaPlayer(){
        mPlayer = MediaPlayer.create(this, R.raw.wildestdreams);
        mPlayer.setLooping(true);
        mPlayer.setVolume(0.50f, 0.50f);
        mPlayer.start();
    }


}
