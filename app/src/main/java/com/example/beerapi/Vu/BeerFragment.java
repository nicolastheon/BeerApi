package com.example.beerapi.Vu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.beerapi.Control.Adapter;
import com.example.beerapi.Control.BeerRestApi;
import com.example.beerapi.Control.OnBeerClick;
import com.example.beerapi.Model.Beer;
import com.example.beerapi.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BeerFragment extends Fragment{
    private View v;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressBar progressBar;
    public List<Beer> listBeer;
    private SharedPreferences sharedPreferences;

    public BeerFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_beer, container, false);
        recyclerView = v.findViewById(R.id.my_recycler_view);
        progressBar = v.findViewById(R.id.loader);
        sharedPreferences=getActivity().getSharedPreferences("done", Context.MODE_PRIVATE);

        if(sharedPreferences.contains("done")){
            showLoader();
            String listB= sharedPreferences.getString("done",null);
            Type listT=new TypeToken<List<Beer>>(){}.getType();
            List<Beer> listBeer=new Gson().fromJson(listB,listT);
            showList(listBeer);
            hideLoader();
        }
        else{
            showLoader();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://ontariobeerapi.ca/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            BeerRestApi beerApi = retrofit.create(BeerRestApi.class);

            Call<List<Beer>> call = beerApi.getListBeer();
            call.enqueue(new Callback<List<Beer>>() {
                @Override
                public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response) {
                    listBeer = response.body();
                    Gson gson = new Gson();
                    String listB = gson.toJson(listBeer);

                    sharedPreferences
                            .edit()
                            .putString("done", listB)
                            .apply();

                    showList(listBeer);
                    hideLoader();
                }
                @Override
                public void onFailure(Call<List<Beer>> call, Throwable t) {
                    Log.d("Erreur", "API erreur");
                }
            });
        }
        return v;
    }

    public void showLoader(){
        progressBar.setVisibility(View.VISIBLE);
    }
    public void hideLoader(){
        progressBar.setVisibility(View.GONE);
    }

    public void showList(List<Beer> listBeer) {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(listBeer, getActivity().getApplicationContext(), new OnBeerClick() {
            @Override
            public void onItemClick(Beer beer) {
                Toast.makeText(getActivity().getApplicationContext(),beer.getName(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity().getApplicationContext(),Beer_Details.class);
                intent.putExtra("name",beer.getName());
                intent.putExtra("price",beer.getPrice());
                intent.putExtra("image",beer.getImage_url());
                intent.putExtra("categorie",beer.getCategory());
                intent.putExtra("style",beer.getStyle());
                intent.putExtra("attributes",beer.getAttributes());
                intent.putExtra("type",beer.getType());
                intent.putExtra("country",beer.getCountry());
                BeerFragment.this.startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
