package tda367.myapplication.controller;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import tda367.myapplication.R;


/**
 * A simple {@link Fragment} subclass.
 * Created by: Hanna Carlsson, revised by Tobias Lindgren
 * Responsibility: Setting the information in the views in Settings fragment
 * and controlling the volume of the background music
 * Used by: MainActivity
 * Uses:
 */
public class SettingsFragment extends Fragment {

    private SeekBar volumeSeekBar;
    private TextView volumeText;
    private ImageButton muteButton;
    private int maxVolume = 100;
    private boolean isMuted = false;
    private int savedVolume;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        volumeSeekBar = (SeekBar)view.findViewById(R.id.volumeSeekBar);
        volumeText = (TextView)view.findViewById(R.id.volumeText);
        muteButton = (ImageButton)view.findViewById(R.id.muteButton);

        volumeSeekBar.setProgress(50);
        volumeText.setText(volumeSeekBar.getProgress() + "");
        volumeSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);

        muteButton.setOnClickListener(muteButtonOnClickListener);

        return view;
    }

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            volumeText.setText(progress + "");
            float log1=(float)(Math.log(maxVolume-(maxVolume-progress))/Math.log(maxVolume));
            LearnJavaController.mPlayer.setVolume(log1, log1);
            isMuted = progress == 0;
            if (isMuted) {
                setMutePicture();
            }
            else {
                setUnMutePicture();
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private View.OnClickListener muteButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!isMuted){
                setSavedVolume(volumeSeekBar.getProgress());
                LearnJavaController.mPlayer.setVolume(0,0);
                volumeSeekBar.setProgress(0);
                isMuted = true;
                setMutePicture();
            }
            else {
                if (savedVolume == 0){
                    setVolumeSettings(50);
                }
                else {
                    setVolumeSettings(savedVolume);
                }
            }
        }
    };

    private void setSavedVolume(int volume){
        savedVolume = volume;
    }

    private void setMutePicture(){
        muteButton.setImageResource(R.mipmap.ic_launcher_mute);

    }

    private void setUnMutePicture(){
        muteButton.setImageResource(R.mipmap.ic_launcher_unmute);
    }

    private void setVolumeSettings(int vol) {
        float log1=(float)(Math.log(maxVolume-(maxVolume-vol))/Math.log(maxVolume));
        LearnJavaController.mPlayer.setVolume(log1, log1);
        volumeSeekBar.setProgress(vol);
        isMuted = false;
        setUnMutePicture();
    }

}
