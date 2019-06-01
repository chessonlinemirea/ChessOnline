package com.example.chess.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.chess.AsyncTasks.AsyncTaskCheckStatistic;
import com.example.chess.R;

public class StatisticActivity extends AppCompatActivity
{


    TextView game;
    TextView win;
    TextView lose;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.statistic);

        findView();

        AsyncTaskCheckStatistic asyncTaskCheckStatistic= new AsyncTaskCheckStatistic(getApplicationContext(), game, win, lose);
        asyncTaskCheckStatistic.execute();
    }

    private void findView()
    {
        game = findViewById(R.id.textView_game);
        win = findViewById(R.id.textView_win);
        lose = findViewById(R.id.textView_lose);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}

