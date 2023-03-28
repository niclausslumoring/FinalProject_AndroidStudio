package com.example.signup.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.signup.Game;
import com.example.signup.GameAdapter;
import com.example.signup.R;
import com.example.signup.gameHelper;

import java.util.Vector;

public class FragmentGame extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        RecyclerView gameRecycler = view.findViewById(R.id.game_rv);
        Vector<Game> game = new Vector<>();
        gameHelper gh = new gameHelper(getContext());

        gh.open();
        game.addAll(gh.viewGame());
        gh.close();

        GameAdapter gameAdapter = new GameAdapter(getContext());
        gameAdapter.setGameVector(game);

        gameRecycler.setAdapter(gameAdapter);
        gameRecycler.setLayoutManager(new GridLayoutManager(getContext(),1));


        return view;
    }
}