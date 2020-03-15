package com.yucatancorp.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.yucatancorp.androidproject.POJOs.Pokemon;
import com.yucatancorp.androidproject.POJOs.Resultado;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.yucatancorp.androidproject.MainActivity.STATUS;

public class PokemonsActivity extends AppCompatActivity {

    SharedPreferences sharedPreferencesStatus;
    SharedPreferencesActions sharedPreferencesActions;

    RecyclerView recyclerView;

    ArrayList<Pokemon> pokemons;

    Retrofit retrofit;

    private String baseURL = "https://pokeapi.co/api/v2/";

    PokemonAdaptador pokemonAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemons);

        recyclerView = findViewById(R.id.recyclerView);
        pokemons = new ArrayList<>();

        GridLayoutManager glm = new GridLayoutManager(PokemonsActivity.this, 3);
        recyclerView.setLayoutManager(glm);
        pokemonAdaptador = new PokemonAdaptador(PokemonsActivity.this);

        cargarPokemons();
        recyclerView.setAdapter(pokemonAdaptador);

        sharedPreferencesStatus = getApplicationContext().getSharedPreferences(STATUS, 0);
        SharedPreferencesActions sharedPreferencesActions = new SharedPreferencesActions(sharedPreferencesStatus);

    }

    public void cargarPokemons() {

        retrofit = new Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create()).build();
        PokemonGets pokemon = retrofit.create(PokemonGets.class);

        Call<Resultado> resultadosObtenidos = pokemon.listaPokemons(30, 0);

        resultadosObtenidos.enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {

                if (response.isSuccessful()) {

                    Resultado resultado = response.body();
                    ArrayList<Pokemon> pokemonsD = resultado.getResults();
                    pokemonAdaptador.gettingData(pokemonsD);
                    Log.i("pokemons", pokemonsD.get(0).getName());
                }
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {

            }
        });
    }
}

