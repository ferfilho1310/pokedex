package com.example.pokedex.Controler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.pokedex.Adapter.AdapterPokemon;
import com.example.pokedex.Model.Pokemon;
import com.example.pokedex.R;
import com.example.pokedex.Refrofit.ApiRefrofit;
import com.example.pokedex.Refrofit.PokeInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rc_pokemon;
    LinearLayoutManager lm_pokemon;
    AdapterPokemon pokemonAdapter;
    SearchView sv_pokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("PokeDex do Fernando");

        rc_pokemon = findViewById(R.id.rc_pokemon);
        sv_pokemon = findViewById(R.id.sv_pokemon);

        lm_pokemon = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        pokemonAdapter = new AdapterPokemon(this);
        rc_pokemon.setAdapter(pokemonAdapter);
        rc_pokemon.setHasFixedSize(true);
        rc_pokemon.setLayoutManager(lm_pokemon);
        rc_pokemon.setItemAnimator(new DefaultItemAnimator());

        adicionarPokens();
        search();
    }

    private void search(){
        sv_pokemon.setIconifiedByDefault(false);
        sv_pokemon.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                pokemonAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void adicionarPokens( ) {

        PokeInterface pokeInterface = ApiRefrofit.getRetrofit().create(PokeInterface.class);

        Call<List<Pokemon>> callPokemon = pokeInterface.getPokemon("https://pokemon-db-json.herokuapp.com/");
        callPokemon.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {

                if (response.isSuccessful()) {
                    List<Pokemon> pokemon = response.body();
                    Log.i("TAG", "types" + pokemon.toString());
                    pokemonAdapter.adicionarListaPokemon(pokemon);
                }
            }
            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {

            }
        });

    }
}

