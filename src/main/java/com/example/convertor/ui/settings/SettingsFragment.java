package com.example.convertor.ui.settings;

import android.content.ContentResolver;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.provider.Settings.SettingNotFoundException;
import android.util.Log;

import android.view.Window;

import android.view.WindowManager.LayoutParams;

import android.provider.Settings.System;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.convertor.R;

public class SettingsFragment extends Fragment {

    private SettingsViewModel settingsViewModel;
    private SeekBar seekBar;
    private int brightness = 0;

    //Content resolver used as a handle to the system's settings

    private ContentResolver cr;

    //Window object, that will store a reference to the current window

    private Window window;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel =
                ViewModelProviders.of(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        seekBar = root.findViewById(R.id.seekBar);

        seekBar.setMax(255);
        //window = getwindow();

        //set the seek bar progress to 1

        seekBar.setKeyProgressIncrement(1);
//        try
//
//        {
//
//            //Get the current system brightness
//
//            //brightness = System.getInt(cResolver, System.SCREEN_BRIGHTNESS);
//
//        }
//
//        catch (SettingNotFoundException e)
//
//        {
//
//            //Throw an error case it couldn't be retrieved
//
//            Log.e("Error", "Cannot access system brightness");
//
//            e.printStackTrace();
//
//        }



        //Set the progress of the seek bar based on the system's brightness

//        seekBar.setProgress(brightness);



        //Register OnSeekBarChangeListener, so it can actually change values

//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                if(i<=20)
//
//                {
//
//                    //Set the brightness to 20
//
//                    brightness=20;
//
//                }
//
//                else //brightness is greater than 20
//
//                {
//
//                    //Set brightness variable based on the progress bar
//
//                    brightness = i;
//
//                }
//
//                //Calculate the brightness percentage
//
//                float perc = (brightness /(float)255)*100;
//
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//               // System.putInt(cResolver, System.SCREEN_BRIGHTNESS, brightness);
//
//                //Get the current window attributes
//
//                LayoutParams layoutpars = window.getAttributes();
//
//                //Set the brightness of this window
//
//                layoutpars.screenBrightness = brightness / (float) 255;
//
//                //Apply attribute changes to this window
//
//                window.setAttributes(layoutpars);
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });

        return root;

    }
}