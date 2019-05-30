package com.example.chess.Data;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.chess.Adapter.PlayersMenuAdapter;
import com.example.chess.Class.MenuPlayer;

import java.util.ArrayList;
import java.util.Collection;

public class MenuPlayers {
    private static ArrayList<MenuPlayer> playersList = new ArrayList<>();
    private static PlayersMenuAdapter playersAdapter;

    public static void setItems(Collection<MenuPlayer> plaers){
        playersList.addAll(plaers);
        update();
    }

    public static void clearItems(){
        playersList.clear();
        update();
    }

    public static void addItem(String str){
        if(!thereIs(str)){
            playersList.add(new MenuPlayer(str));
            update();
        }
    }

    public static void addItemPlay(String str){
        if (!thereIs(str)){
            playersList.add(new MenuPlayer(str, true));
            Sort();
            update();
        }
    }

    public static void isPlay(int i){
        playersList.get(i).setBoolStatus(true);
        Sort();
        update();
    }

    public static void toIvite(String name){
        boolean f = false;
        for (MenuPlayer item : playersList){
            if (item.getName().equals(name)){
                isPlay(playersList.indexOf(item));
                f = true;
            }
        }
        if (!f){
            addItemPlay(name);
        }
    }

    public static boolean thereIs(String name){
        boolean f = false;
        for(MenuPlayer item : playersList){
            if (item.getName().equals(name)){
                f = true;
            }
        }

        return f;
    }

    public static int getSize(){
        int count = 0;
        for (int i = 0; i < playersList.size(); i++) {
            if(playersList.get(i).isBoolStatus()) count++;
        }
        return count;
    }

    public static String getName(int i){
        if (playersList.get(i).isBoolStatus()){
            return playersList.get(i).getName();
        }
        else return "";
    }

    public static void Sort(){
        int startIndex = 0;
        int endIndex = playersList.size() - 1;
        doSort(startIndex, endIndex);
    }
    public static void doSort(int start, int end) {
        if (start >= end)
            return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (Boolean.compare(playersList.get(i).isBoolStatus(), false) >= Boolean.compare(playersList.get(cur).isBoolStatus(), false))) {
                i++;
            }
            while (j > cur && (Boolean.compare(playersList.get(cur).isBoolStatus() ,false) >= Boolean.compare(playersList.get(j).isBoolStatus(), false))) {
                j--;
            }
            if (i < j) {
                MenuPlayer temp = playersList.get(i);
                playersList.set(i,playersList.get(j));
                playersList.set(j,temp);
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        doSort(start, cur);
        doSort(cur+1, end);
    }

    public static void buildRecyclerView(final Context context, RecyclerView players) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        players.setLayoutManager(layoutManager);
        playersAdapter = new PlayersMenuAdapter(context);
        players.setAdapter(playersAdapter);
        playersAdapter.setOnItemClickListner(new PlayersMenuAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(int position)
            {
                //Log.d("asyncTask", "invite");
                /*AsyncTaskInvite asyncTaskInvite = new AsyncTaskInvite();
                asyncTaskInvite.setLogin(playersList.get(position).getName(), context);
                asyncTaskInvite.execute();*/
                //isPlay(position);
            }
        });
    }

    public static ArrayList<MenuPlayer> getPlayersList() {
        return playersList;
    }

    public static PlayersMenuAdapter getPlayersAdapter() {
        return playersAdapter;
    }

    private static void update()
    {
        if(playersAdapter != null)
        {
            playersAdapter.update();
        }
    }
}
