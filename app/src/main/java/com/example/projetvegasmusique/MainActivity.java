package com.example.projetvegasmusique;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.projetvegasmusique.DAO.GestionBDD;
import com.example.projetvegasmusique.metier.Playlist;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Playlist> lesPlaylists = new ArrayList<Playlist>();
    GestionBDD sgbd = new GestionBDD(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}