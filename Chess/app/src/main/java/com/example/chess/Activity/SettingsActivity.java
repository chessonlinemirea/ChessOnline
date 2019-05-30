package com.example.chess.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;

import com.example.chess.R;

public class SettingsActivity extends AppCompatActivity
{
    private static int seekProgress = 100; //проценты в seek bar
    private static boolean switchProgress = false; //проверка на включение кнопки вибрации

    private Button buttonExit; // кнопка выхода в меню
    private Button browseButton; // кнопка открыть правила в интернете
    private Switch aSwitch;
    private SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.settings);

        findView();

        seekBar.setProgress(seekProgress);
        aSwitch.setChecked(switchProgress);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                switchProgress = aSwitch.isChecked();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                seekProgress = seekBar.getProgress();
            }
        });

        View.OnClickListener clickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                switch(v.getId())
                {
                    case R.id.buttonExitFromSettings:
                        finish();
                        break;
                    case R.id.browseButton:
                        String url = "https://munchkin-game.fandom.com/ru/wiki/Правила_игры_Манчкин";
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url)); startActivity(i);
                        break;
                }
            }
        };

        browseButton.setOnClickListener(clickListener);
        buttonExit.setOnClickListener(clickListener);
    }

    private void findView()
    {
        aSwitch = (Switch)findViewById(R.id.switchVibration);
        seekBar = (SeekBar)findViewById(R.id.scrollSound);
        buttonExit = (Button)findViewById(R.id.buttonExitFromSettings);
        browseButton = (Button)findViewById(R.id.browseButton);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}

