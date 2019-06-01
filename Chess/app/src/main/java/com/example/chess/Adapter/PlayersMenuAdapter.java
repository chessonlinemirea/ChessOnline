package com.example.chess.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chess.AsyncTasks.AsyncTaskInvite;
import com.example.chess.AsyncTasks.AsyncTaskUpdate;
import com.example.chess.Class.MenuPlayer;
import com.example.chess.Data.MenuPlayers;
import com.example.chess.Data.PlayerInstances;
import com.example.chess.R;

public class PlayersMenuAdapter extends RecyclerView.Adapter<PlayersMenuAdapter.PlayerViewHolder>{

    private OnItemClickListener listener;
    private Context context;

    public PlayersMenuAdapter(Context context) {
        this.context = context;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void update()
    {
        notifyDataSetChanged();
    }



    public void setOnItemClickListner(OnItemClickListener listner){
        this.listener = listener;
    }


    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.recycleview_item_players_menu, viewGroup, false);
        PlayerViewHolder viewHolder = new PlayerViewHolder(view, listener);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder playerViewHolder, int i) {
        playerViewHolder.bind(MenuPlayers.getPlayersList().get(i));
    }

    @Override
    public int getItemCount() {
        return MenuPlayers.getPlayersList().size();
    }

    class PlayerViewHolder extends RecyclerView.ViewHolder{

        TextView listNames;
        TextView listStatus;
        ImageView plus;
        ImageView minus;

        public PlayerViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            listNames = itemView.findViewById(R.id.tv_name);
            listStatus = itemView.findViewById(R.id.tv_status);
            plus = itemView.findViewById(R.id.imageView_plus);
            minus = itemView.findViewById(R.id.imageView_minus);

            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("plus", "click");
                    if (PlayerInstances.getPlayer().isInvite() == false)
                    {
                        AsyncTaskInvite asyncTaskInvite = new AsyncTaskInvite(MenuPlayers.getPlayersList().get(getAdapterPosition()).getName(), context);
                        asyncTaskInvite.execute();
                    }
                    else
                    {
                        Log.d("isInvite", "true");
                    }

                    //isPlay(position);
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!MenuPlayers.getName(0).equals("")){
                        AsyncTaskUpdate asyncTaskUpdate = new AsyncTaskUpdate(context);
                        asyncTaskUpdate.execute();
                    }
                    else {
                        Toast.makeText(context, "Вы не в лобби", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        void bind(MenuPlayer plaer){
            listNames.setText(plaer.getName());
            if (plaer.isBoolStatus()){
                listStatus.setText("play");
                listStatus.setTextColor(Color.GREEN);
            }
            else {
                listStatus.setText("");
            }
        }
    }
}
