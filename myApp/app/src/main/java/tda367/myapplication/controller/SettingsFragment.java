package tda367.myapplication.controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import tda367.myapplication.R;
import tda367.myapplication.service.BackgroundMusicService;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    SeekBar volumeSeekBar;
    TextView settingsTitle;
    TextView volumeText;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        volumeSeekBar = (SeekBar)view.findViewById(R.id.volumeSeekBar);
        settingsTitle = (TextView)view.findViewById(R.id.settingsTitle);
        volumeText = (TextView)view.findViewById(R.id.volumeText);
        volumeText.setText(volumeSeekBar.getProgress() + "");
        volumeSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        return view;
    }

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            volumeText.setText(progress + "");
            BackgroundMusicService.getInstance().setVolume(progress, progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

}
