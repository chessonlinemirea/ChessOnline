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

import com.example.chess.Class.MenuPlayer;
import com.example.chess.Data.Data;
import com.example.chess.Data.MenuPlayers;
import com.example.chess.R;

import java.util.ArrayList;

public class MoveHistoryAdapter extends RecyclerView.Adapter<MoveHistoryAdapter.MoveViewHolder>{

    private OnItemClickListener listener;
    private Context context;

    ArrayList<String> moves = new ArrayList<>();
    ArrayList<String> ways = new ArrayList<>();

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void add(String move, String way){
        moves.add(move);
        ways.add(way);
        Log.d(move, way);
        notifyDataSetChanged();
    }



    public void setOnItemClickListner(OnItemClickListener listner){
        this.listener = listener;
    }


    @NonNull
    @Override
    public MoveViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.recycleview_item_move_game, viewGroup, false);
        MoveViewHolder viewHolder = new MoveViewHolder(view, listener);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MoveViewHolder playerViewHolder, int i) {
        playerViewHolder.bind(moves.get(i), ways.get(i));
    }

    @Override
    public int getItemCount() {
        return moves.size();
    }

    class MoveViewHolder extends RecyclerView.ViewHolder{

        TextView start;
        TextView end;
        ImageView way;

        public MoveViewHolder(View itemView, final OnItemClickListener listener) {

            super(itemView);

            start = itemView.findViewById(R.id.textView_start);
            end = itemView.findViewById(R.id.textView_end);
            way = itemView.findViewById(R.id.way_log);

        }

        void bind(String move, String sWay){
            String splitMove[] = move.split(" ");
            int startColumn = Integer.valueOf(splitMove[0]) + 1;
            int startLine = Integer.valueOf(splitMove[1]) + 1;
            int endColumn = Integer.valueOf(splitMove[2]) + 1;
            int endLine = Integer.valueOf(splitMove[3]) + 1;
            Log.d("way", sWay);
            way.setImageResource(getImageId(itemView.getContext(), sWay));
            start.setText("(" + startColumn + ", " + startLine + ")");
            end.setText("(" + endColumn + ", " + endLine + ")");
        }
    }
    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
}
