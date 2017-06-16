package com.afrasali.afras.finaltest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Afras Ali on 6/16/2017.
 */

class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private Context context;
    private List<MyData> data_list;

    public CustomAdapter(NewsFeed mainActivity, List<MyData> data_list) {
        this.context=mainActivity;
        this.data_list=data_list;
    }

    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CustomAdapter.ViewHolder holder, int position) {
        holder.CountryName.setText(data_list.get(position).getCountryName());
        holder.CountryPopulation.setText(data_list.get(position).getCountryPopulation());
        Glide.with(context).load("http://www.androidbegin.com/tutorial/jsonparsetutorial").into(holder.img);

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView CountryName,CountryPopulation,Rank;
        public ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            CountryName =(TextView)itemView.findViewById(R.id.country);
            CountryPopulation=(TextView)itemView.findViewById(R.id.population);
            img=(ImageView)itemView.findViewById(R.id.pic);
            Rank=(TextView)itemView.findViewById(R.id.rank);

        }
    }
}

