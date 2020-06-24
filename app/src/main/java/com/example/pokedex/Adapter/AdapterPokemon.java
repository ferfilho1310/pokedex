package com.example.pokedex.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex.Model.Pokemon;
import com.example.pokedex.Model.Types;
import com.example.pokedex.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.POST;

public class AdapterPokemon extends RecyclerView.Adapter<AdapterPokemon.ViewHolderPokemon> implements Filterable {

    List<Pokemon> dataset;
    List<Pokemon> datasetful;
    Context context;

    public AdapterPokemon(Context context) {
        dataset = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderPokemon onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolderPokemon(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPokemon holder, int position) {

        Pokemon pokemon = dataset.get(position);
        holder.txt_pokemon.setText(pokemon.getName().toUpperCase());
        holder.txt_descricao.setText(pokemon.getDescription());
        holder.txt_types.setText(pokemon.types());

        Log.i("TAG", "tipos de pokekon" + pokemon.getTypespokemon());

        Picasso.get()
                .load(pokemon.getImage())
                .resize(200, 200)
                .into(holder.img_pokemon);
    }

    public void adicionarListaPokemon(List<Pokemon> listaPokemon) {
        dataset.addAll(listaPokemon);
        datasetful = new ArrayList<>(listaPokemon);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataset != null ? dataset.size() : null;
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Pokemon> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(datasetful);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Pokemon item : datasetful){
                    if(item.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataset.clear();
            dataset.addAll((List) results.values);
            notifyDataSetChanged();

        }
    };

    public class ViewHolderPokemon extends RecyclerView.ViewHolder {

        ImageView img_pokemon;
        TextView txt_pokemon, txt_descricao, txt_types;

        public ViewHolderPokemon(@NonNull View itemView) {
            super(itemView);

            img_pokemon = itemView.findViewById(R.id.img_pokemon);
            txt_pokemon = itemView.findViewById(R.id.txt_name);
            txt_descricao = itemView.findViewById(R.id.txt_descriçao);
            txt_types = itemView.findViewById(R.id.txt_types);
        }
    }

}
