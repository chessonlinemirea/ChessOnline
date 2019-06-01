package com.example.chess.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chess.AsyncTasks.AsyncTaskCheckInviteResult;
import com.example.chess.AsyncTasks.AsyncTaskCheckIvite;
import com.example.chess.AsyncTasks.AsyncTaskCheckPlay;
import com.example.chess.AsyncTasks.AsyncTaskCheckStatistic;
import com.example.chess.AsyncTasks.AsyncTaskCheckUpdate;
import com.example.chess.AsyncTasks.AsyncTaskExit;
import com.example.chess.AsyncTasks.AsyncTaskPlay;
import com.example.chess.AsyncTasks.AsyncTaskStatus;
import com.example.chess.AsyncTasks.AsyncTaskUpdate;
import com.example.chess.Data.MenuPlayers;
import com.example.chess.Data.PlayerInstances;
import com.example.chess.R;

import java.util.Timer;
import java.util.TimerTask;

public class MainMenuActivity extends AppCompatActivity
{

    private RecyclerView playersList;        //лист с игрками

    public static String[] Players = new String[3];
    public static int SizePlayers;
    public static boolean checkPlay = false;
    public static Timer timer;
    public static boolean checkDialogInvite = false;

    private EditText search;      // строка поиска
    private ImageView plusSearch; //кнопка плюсик в строке поиска
    private Button play;
    private Button settings;
    private Button exit;
    private ImageView update;
    TextView playerName;
    TextView playerId;

    private boolean status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_menu);



        search = findViewById(R.id.search);
        plusSearch = findViewById(R.id.imageView_search_plus);
        playersList = findViewById(R.id.recyclerView_moves);
        play = findViewById(R.id.button_play);
        settings = findViewById(R.id.button_settings);
        exit = findViewById(R.id.button_exit);
        update = findViewById(R.id.imageView_update);
        playerName = findViewById(R.id.playerName);
        playerId = findViewById(R.id.playerId);
        playerName.setText(PlayerInstances.getPlayer().getName());
        playerId.setText(String.valueOf(PlayerInstances.getPlayer().getId()));


        View.OnClickListener clickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                switch(v.getId())
                {
                    case R.id.button_play:
                        if (!MenuPlayers.getName(0).equals("")){
                            AsyncTaskPlay asyncTaskPlay = new AsyncTaskPlay(getApplicationContext(), play);
                            asyncTaskPlay.execute();
                            play.setTextColor(Color.GREEN);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Пригласите игрока", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.button_exit:
                        search.setText("exit");
                        finish();
                        break;
                    case R.id.button_settings:
                        startActivity(new Intent(MainMenuActivity.this, StatisticActivity.class));
                        break;
                    case R.id.imageView_search_plus:
                        status = false;
                        if (String.valueOf(search.getText()).equals(PlayerInstances.getPlayer().getName()))
                        {
                            Toast.makeText(getApplicationContext(), "Вы приглашете самого себя", Toast.LENGTH_LONG).show();
                        }
                        else if (String.valueOf(search.getText()).equals(""))
                        {
                            Toast.makeText(getApplicationContext(), "Введите логин игрока", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            AsyncTaskStatus asyncTaskStatus = new AsyncTaskStatus(String.valueOf(search.getText()), getApplicationContext());
                            asyncTaskStatus.execute();
                        }
                        search.setText(null);
                        Log.d("asyncTask", "status");
                        break;
                    case R.id.imageView_update:

                        break;
                }
                MenuPlayers.getPlayersAdapter().update();
            }
        };

        plusSearch.setOnClickListener(clickListener);
        update.setOnClickListener(clickListener);
        play.setOnClickListener(clickListener);
        exit.setOnClickListener(clickListener);
        settings.setOnClickListener(clickListener);

        bildRecyclerView(); //cборка листа
        timer = new Timer();
        timer.schedule(new UpdateTimeTask(), 0, 3000);
    }
    class UpdateTimeTask extends TimerTask {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //Log.d("check", String.valueOf(PlayerInstances.getPlayer().isInvite()));
                    if (checkDialogInvite == false){
                        AsyncTaskCheckIvite asyncTaskCheckIvite = new AsyncTaskCheckIvite(MainMenuActivity.this);
                        asyncTaskCheckIvite.execute();
                    }
                    if (PlayerInstances.getPlayer().isInvite() == true){
                        AsyncTaskCheckInviteResult asyncTaskCheckInviteResult = new AsyncTaskCheckInviteResult(MainMenuActivity.this);
                        asyncTaskCheckInviteResult.execute();
                    }
                    if (!MenuPlayers.getName(0).equals("")){
                        AsyncTaskCheckUpdate asyncTaskCheckUpdate = new AsyncTaskCheckUpdate(getApplicationContext(), MenuPlayers.getName(0));
                        asyncTaskCheckUpdate.execute();
                    }
                    AsyncTaskCheckPlay asyncTaskCheckPlay = new AsyncTaskCheckPlay(getApplicationContext(),MainMenuActivity.this);
                    asyncTaskCheckPlay.execute();
                }
            });

        }
    }

    public void bildRecyclerView()
    {
        MenuPlayers.buildRecyclerView(this, playersList);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//    }

    @Override
    protected void onDestroy()
    {
        AsyncTaskExit asyncTaskExit = new AsyncTaskExit(getApplicationContext());
        asyncTaskExit.execute();
        timer.cancel();
        super.onDestroy();
    }
}
