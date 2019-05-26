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
        Data.create();
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
    void write() {
        // проверяем доступность SD
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "SD-карта не доступна: " + Environment.getExternalStorageState(), Toast.LENGTH_LONG).show();
            return;
        }
        // получаем путь к SD
        File sdPath = Environment.getDataDirectory();
        // добавляем свой каталог к пути
        sdPath = new File(sdPath.getAbsolutePath() + "/" + "Documents");
        // создаем каталог
        sdPath.mkdirs();
        // формируем объект File, который содержит путь к файлу
        File sdFile = new File(sdPath, "fileaver");
//        try {
//            // открываем поток для записи
//            BufferedWriter bw = new BufferedWriter(new FileWriter(sdFile));
//            // пишем данные
//            bw.write("Содержимое файла на SD");
//            // закрываем поток
//            bw.close();
//            Toast.makeText(this, "Файл записан на SD: " + sdFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
//        } catch (IOException e) {
//            e.printStackTrace();
//            Toast.makeText(this, "404", Toast.LENGTH_LONG).show();
//        }
        try {
        FileOutputStream f = new FileOutputStream(sdFile);
        PrintWriter pw = new PrintWriter(f);
        pw.println("Содержимое файла на SD");
        pw.flush();
        pw.close();
        f.close();
        // Log.v(TAG, "file written to sd card");
            Toast.makeText(this, "Файл записан на SD: " + sdFile.getAbsolutePath(), Toast.LENGTH_LONG).show();

    } catch (FileNotFoundException e) {

        e.printStackTrace();

        // Log.i(TAG, "******* File not found. Did you" +

        // " add a WRITE_EXTERNAL_STORAGE permission to the manifest?");
            Toast.makeText(this, "404", Toast.LENGTH_LONG).show();

    } catch (IOException e) {

        e.printStackTrace();


    }
    }

    void read(){
        try {
            FileInputStream fos = openFileInput("file.txt");
            InputStreamReader reader = new InputStreamReader(fos);
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuffer stringBuffer = new StringBuffer();
            String text;
            while ((text = bufferedReader.readLine()) != null){
                stringBuffer.append(text + "\n");
            }
            Toast.makeText(this, stringBuffer.toString(), Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
