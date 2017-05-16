package tda367.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import tda367.myapplication.R;

/**
 * Created by Tubas on 2017-05-16.
 * http://stackoverflow.com/questions/8209858/android-background-music-service/8209975#8209975
 */

public class BackgroundMusicService extends Service {
    private static final String TAG = null;
    private MediaPlayer mPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mPlayer = MediaPlayer.create(this, R.raw.wildestdreams);
        mPlayer.setLooping(true);
        mPlayer.setVolume(100,100);

    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        mPlayer.start();
        return START_STICKY;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        //TODO
    }

    public IBinder onUnBind(Intent intent) {
        return null;
    }

    public void onStop() {

    }

    public void onPause() {

    }

    @Override
    public void onDestroy() {
        mPlayer.stop();
        mPlayer.release();
    }

    @Override
    public void onLowMemory() {

    }

}
