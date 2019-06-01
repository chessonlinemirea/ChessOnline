package com.example.chess.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.chess.Adapter.MoveHistoryAdapter;
import com.example.chess.Adapter.PlayersMenuAdapter;
import com.example.chess.AsyncTasks.AsyncTaskCheckMove;
import com.example.chess.AsyncTasks.AsyncTaskEndGame;
import com.example.chess.AsyncTasks.AsyncTaskPlay;
import com.example.chess.AsyncTasks.AsyncTaskStatus;
import com.example.chess.AsyncTasks.AsyncTaskUpdate;
import com.example.chess.Data.Data;
import com.example.chess.Data.MenuPlayers;
import com.example.chess.Data.PlayerInstances;
import com.example.chess.R;

import java.util.Timer;
import java.util.TimerTask;

import static com.example.chess.Data.Data.canMove;


public class GameActivity extends AppCompatActivity {

    private RecyclerView columns;
    private RecyclerView moves;
    Button leave;
    private static MoveHistoryAdapter moveHistoryAdapter;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        columns = findViewById(R.id.columns);
        moves = findViewById(R.id.recyclerView_moves);
        leave = findViewById(R.id.button_leave);

        View.OnClickListener clickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                switch(v.getId())
                {
                    case R.id.button_leave:
                        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("Вы точно хотите сдаться?")
                                .setCancelable(false)
                                .setPositiveButton("Да",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                AsyncTaskEndGame asyncTaskEndGame = new AsyncTaskEndGame(GameActivity.this, false);
                                                asyncTaskEndGame.execute();
                                                dialog.dismiss();
                                            }
                                        })
                                .setNegativeButton("Нет",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {

                                                dialog.dismiss();
                                            }
                                        });


                        AlertDialog alert = builder.create();
                        alert.show();
                        break;
                }
                MenuPlayers.getPlayersAdapter().update();
            }
        };
        leave.setOnClickListener(clickListener);


        Data.create(GameActivity.this, moveHistoryAdapter);
        bildRecyclerView();
        timer = new Timer();
        timer.schedule(new UpdateTimeTask(), 0, 2000);
    }

    @Override
    protected void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }

    public void bildRecyclerView() {
        LinearLayoutManager layoutManagerPlayers = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        columns.setLayoutManager(layoutManagerPlayers);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(columns.getContext(),
                layoutManagerPlayers.getOrientation());
        columns.addItemDecoration(dividerItemDecoration);

        layoutManagerPlayers = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        moves.setLayoutManager(layoutManagerPlayers);
        dividerItemDecoration = new DividerItemDecoration(columns.getContext(),
                layoutManagerPlayers.getOrientation());
        moves.addItemDecoration(dividerItemDecoration);
        Data.bildRecyclerView(columns, moves);
    }

    class UpdateTimeTask extends TimerTask {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (!canMove){
                        AsyncTaskCheckMove asyncTaskCheckMove = new AsyncTaskCheckMove(GameActivity.this);
                        asyncTaskCheckMove.execute();
                    }
                }
            });

        }
    }

}
