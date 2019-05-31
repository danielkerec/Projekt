package com.example.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Restavracija_item> mRestavracijaList;
    private OnItemClickListener mListener;


    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    // stores and recycles views as they are scrolled off screen
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView ime;
        public TextView naslov;
        public ImageView delete;
        public RatingBar rtnBar;

        public TextView getIme() {
            return ime;
        }

        public TextView getNaslov() {
            return naslov;
        }

        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            ime = itemView.findViewById(R.id.text_prikaz_restavracij_item);
            naslov = itemView.findViewById(R.id.text_prikaz_restavracij_item_naslov);
            delete = itemView.findViewById(R.id.delete_icon);
            rtnBar = itemView.findViewById(R.id.ratingBar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    public MyRecyclerViewAdapter(ArrayList<Restavracija_item> listaRestavracij) {
        mRestavracijaList = listaRestavracij;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.prikaz_restavracij_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Restavracija_item trenutni = mRestavracijaList.get(i);

        viewHolder.ime.setText(trenutni.getIme());
        viewHolder.naslov.setText(trenutni.getNaslov());
        viewHolder.rtnBar.setRating(trenutni.getOcena());
    }

    @Override
    public int getItemCount() {
        return mRestavracijaList.size();
    }

    public ArrayList<Restavracija_item> getmRestavracijaList() {
        return mRestavracijaList;
    }
}
