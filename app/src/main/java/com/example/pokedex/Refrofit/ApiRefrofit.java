package com.example.pokedex.Refrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRefrofit {

    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit(){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if(retrofit == null){
         retrofit = new Retrofit.Builder()
                 .baseUrl("https://pokemon-db-json.herokuapp.com/")
                 .addConverterFactory(GsonConverterFactory.create(gson))
                 .build();
        }
        return retrofit;
    }
}
