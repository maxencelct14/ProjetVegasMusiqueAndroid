package com.example.projetvegasmusique;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.example.projetvegasmusique.DAO.GestionBDD;

public class VoirPlaylistActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_playlist);
        Bundle extras = getIntent().getExtras();
        String lId = extras.getString("lId");
        String leTitrePlaylist = extras.getString("leTitrePlaylist");
    }

}
