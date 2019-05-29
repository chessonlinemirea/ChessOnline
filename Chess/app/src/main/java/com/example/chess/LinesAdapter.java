package com.example.chess;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class LinesAdapter extends RecyclerView.Adapter<LinesAdapter.LineViewHolder> {

    private ArrayList<Cell> arrayList;

    private OnItemClickListner listner;

    public interface OnItemClickListner{
        void onItemClick(int position);
    }

    public void setOnItemClickListner(OnItemClickListner listner){
        this.listner = listner;
    }

    public void addAll(ArrayList<Cell> list){
        arrayList = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public LineViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.lines_item, viewGroup, false);
        return new LineViewHolder(view, listner);
    }

    @Override
    public void onBindViewHolder(@NonNull LineViewHolder lineViewHolder, int i) {
        lineViewHolder.bind(arrayList.get(i));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class LineViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        ImageView way;

        public LineViewHolder(@NonNull final View itemView, final OnItemClickListner listner) {
            super(itemView);

            imageView = (ImageView)itemView.findViewById(R.id.imageView);
            way = (ImageView)itemView.findViewById(R.id.way);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listner != null){
                        listner.onItemClick(getAdapterPosition());
                        //Toast.makeText(itemView.getContext(), "404", Toast.LENGTH_LONG).show();
                        notifyDataSetChanged();
                    }
                }
            });
        }


        @SuppressLint("ResourceAsColor")
        void bind(Cell cell){

            imageView.setImageResource(getImageId(itemView.getContext(), cell.toString()));
            if (cell.isPoint()) {
                //Log.d("linesAdapter", cell.getColumn() + " " + cell.getLine());
                if (cell.getPointColorEnum() == PointColorEnum.GREEN){
                    way.setImageResource(R.drawable.pointgreem);
                }
                else if (cell.getPointColorEnum() == PointColorEnum.RED){
                    way.setImageResource(R.drawable.pointred);
                }
            }
            else {
                way.setImageDrawable(null);
            }
            imageView.setBackgroundColor(cell.getFieldColor());
        }

    }

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
}
