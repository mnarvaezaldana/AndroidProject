package com.yucatancorp.androidproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PokemonAdaptador extends RecyclerView.Adapter<PokemonAdaptador.PokemonViewHolder>{

    private ArrayList<Pokemon> pokemons;

    public PokemonAdaptador(ArrayList<Pokemon> pokemons){
        this.pokemons = pokemons;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_card, parent);
        return new PokemonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder pokemonViewholder, int position) {
        Pokemon pokemon = pokemons.get(position);
        pokemonViewholder.imageView.setImageResource(pokemon.getFotoPokemon());
        pokemonViewholder.textView.setText(pokemon.getNombrePokemon());
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public static class PokemonViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView textView;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imagenCV);
            textView = itemView.findViewById(R.id.nombrePokemonCV);
        }
    }
}
