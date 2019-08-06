package com.example.secondapplication;

import android.os.Bundle;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myseekbarlibrary.progressbar.CircleProgressBar;
import com.example.myseekbarlibrary.progressbar.ColorProgressBar;
import com.example.myseekbarlibrary.seekbar.CircleSeekBar;
import com.example.myseekbarlibrary.seekbar.ColorSeekBar;

public class MainActivity extends AppCompatActivity {
    ColorProgressBar cpb_test;
    CircleProgressBar cpb_test1;
    ColorSeekBar colSB_test;
    CircleSeekBar csb_test;
    SeekBar start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cpb_test= findViewById(R.id.cpb_test);
        colSB_test= findViewById(R.id.colSB_test);
        csb_test= findViewById(R.id.csb_test);
        cpb_test1= findViewById(R.id.cpb_test1);
        start= findViewById(R.id.start);
        colSB_test.setProgressChange(progress -> {
            cpb_test.setProgress(progress);
            csb_test.setProgress(progress);
            colSB_test.setProgress(progress);
            cpb_test1.setProgress(progress);
        });

        csb_test.setProgressChange(progress -> {
            cpb_test.setProgress(progress);
            csb_test.setProgress(progress);
            colSB_test.setProgress(progress);
            cpb_test1.setProgress(progress);
        });
        start.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                cpb_test.setProgress((float)i/100);
                csb_test.setProgress((float)i/100);
                colSB_test.setProgress((float)i/100);
                cpb_test1.setProgress((float)i/100);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

}
