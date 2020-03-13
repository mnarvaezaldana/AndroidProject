package com.yucatancorp.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import java.util.ArrayList;

import static com.yucatancorp.androidproject.MainActivity.STATUS;

public class PokemonsActivity extends AppCompatActivity {

    SharedPreferences sharedPreferencesStatus;
    SharedPreferencesActions sharedPreferencesActions;

    RecyclerView recyclerView;

    ArrayList<Pokemon> pokemons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemons);

        recyclerView = findViewById(R.id.recyclerView);
        pokemons = new ArrayList<>();

        LinearLayoutManager llm = new LinearLayoutManager(PokemonsActivity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(llm);
        PokemonAdaptador pokemonAdaptador = new PokemonAdaptador(pokemons);

        recyclerView.setAdapter(pokemonAdaptador);

        sharedPreferencesStatus = getApplicationContext().getSharedPreferences(STATUS, 0);
        SharedPreferencesActions sharedPreferencesActions = new SharedPreferencesActions(sharedPreferencesStatus);

    }

    public class obtenerDatos extends AsyncTask<String, Void, String>{


        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }
}
