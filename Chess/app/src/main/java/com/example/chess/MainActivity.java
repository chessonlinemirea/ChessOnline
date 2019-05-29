package com.example.chess;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


public class MainActivity extends AppCompatActivity {

    private RecyclerView columns;
    private ColumnsAdapter columnsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e)
        {

        }
        setContentView(R.layout.activity_main);

        columns = findViewById(R.id.columns);
        Data.create(getApplicationContext());
        bildRecyclerView();
    }

    public void bildRecyclerView() {
        LinearLayoutManager layoutManagerPlayers = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        columns.setLayoutManager(layoutManagerPlayers);
        columnsAdapter = new ColumnsAdapter();
        columns.setAdapter(columnsAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(columns.getContext(),
                layoutManagerPlayers.getOrientation());
        columns.addItemDecoration(dividerItemDecoration);
    }

}
