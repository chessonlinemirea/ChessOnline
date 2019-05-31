package com.example.chess.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.example.chess.AsyncTasks.AsyncTaskCheckMove;
import com.example.chess.Data.Data;
import com.example.chess.R;

import java.util.Timer;
import java.util.TimerTask;

import static com.example.chess.Data.Data.canMove;


public class GameActivity extends AppCompatActivity {

    private RecyclerView columns;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        columns = findViewById(R.id.columns);
        Data.create(getApplicationContext());
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
        Data.bildRecyclerView(columns);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(columns.getContext(),
                layoutManagerPlayers.getOrientation());
        columns.addItemDecoration(dividerItemDecoration);
    }

    class UpdateTimeTask extends TimerTask {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (!canMove){
                        AsyncTaskCheckMove asyncTaskCheckMove = new AsyncTaskCheckMove(getApplicationContext());
                        asyncTaskCheckMove.execute();
                    }
                }
            });

        }
    }

}
