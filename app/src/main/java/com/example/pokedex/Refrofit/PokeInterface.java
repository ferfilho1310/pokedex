package com.example.pokedex.Refrofit;

import com.example.pokedex.Model.Pokemon;
import com.example.pokedex.Model.Sprites;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface PokeInterface {
    @GET()
    Call<List<Pokemon>> getPokemon(@Url String url);
}
