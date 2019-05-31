package com.example.chess.AsyncTasks;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.chess.Data.MenuPlayers;
import com.example.chess.Data.PlayerInstances;
import com.example.chess.Player.Player;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static com.example.chess.Activity.MainMenuActivity.timer;

public class AsyncTaskCheckPlay extends AsyncTask<String, String, String> {
    private String  answerHTTP;

    Context context;
    Activity activity;

    public AsyncTaskCheckPlay(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    String server = "http://jws-app-chess.7e14.starter-us-west-2.openshiftapps.com/api/checkplay";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        HashMap<String,String> postDataParams = new HashMap<>();
        postDataParams.put("login", PlayerInstances.getPlayer().getName());
        answerHTTP = performPostCall(server,postDataParams);
        Log.d("CheckPlay",answerHTTP);
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Toast toast = Toast.makeText(context,answerHTTP,Toast.LENGTH_SHORT);
        if (answerHTTP.equals("1")){
            Toast.makeText(context, "Старт игры", Toast.LENGTH_LONG).show();
            if(MenuPlayers.getSize() == 1 && !MenuPlayers.getName(0).equals("")){
                PlayerInstances.addPlayer(new Player(MenuPlayers.getName(0)));
            }
            //transfer();
            timer.cancel();
            AsyncTaskStartGame asyncTaskCheckColor = new AsyncTaskStartGame(context, activity);
            asyncTaskCheckColor.execute();
        }
    }

    public void transfer()
    {
//        SizePlayers = MenuPlayers.getSize();
//        for (int i = 0; i < SizePlayers; i++)
//        {
//            if (MenuPlayers.getName(i) != "")
//            {
//                Players[i] = MenuPlayers.getName(i);
//            }
//        }
    }

    public String performPostCall(String requestUrl, HashMap<String, String> postDataParams){
        URL url;
        String response = "";
        try{
            url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            writer.write(getDataString(postDataParams));
            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while((line = br.readLine()) != null)
                    response += line;
            }
            else response = "";
        } catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
    private String getDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}
