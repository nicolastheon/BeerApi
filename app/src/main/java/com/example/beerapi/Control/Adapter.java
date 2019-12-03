package com.example.beerapi.Control;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.beerapi.Model.Beer;
import com.example.beerapi.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    private final OnBeerClick click;
    private List<Beer> beerList;
    private Context context;


    public Adapter(List<Beer> dataBase, Context context, OnBeerClick click)
    {
        beerList=dataBase;
        this.context=context;
        this.click=click;
    }



    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView nameBeer;
        public View layout;


        public ViewHolder(View vu)
        {
            super(vu);
            layout = vu;
            nameBeer = (TextView) vu.findViewById(R.id.beer_name);
        }
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View vu=inflater.inflate(R.layout.beer_row,parent,false);
        ViewHolder vuH = new ViewHolder(vu);
        return vuH;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position)
    {
        final Beer beerCurrent = beerList.get(position);
        final String name=beerCurrent.getName();

        holder.nameBeer.setText(name);

        holder.itemView.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onItemClick(beerCurrent);
            }
        }));
    }

    @Override
    public int getItemCount()
    {
        return beerList.size();
    }
}
