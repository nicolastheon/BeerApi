package com.example.beerapi.Control;

import com.example.beerapi.Model.Beer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BeerRestApi {
        @GET("beers")
        Call<List<Beer>> getListBeer();
    }
