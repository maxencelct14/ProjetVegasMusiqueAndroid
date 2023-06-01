package com.example.projetvegasmusique;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projetvegasmusique.DAO.GestionBDD;
import com.example.projetvegasmusique.metier.Playlist;
import com.example.projetvegasmusique.support.PlaylistAdapter;
import com.example.projetvegasmusique.support.TraitementJSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CreerPlaylistActivity extends AppCompatActivity {

    List<Playlist> lesPlaylists = new ArrayList<Playlist>();
    GestionBDD sgbd = new GestionBDD(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voir_playlists);
        //List<Playlist> lesPlaylists = new ArrayList<Playlist>();
        RecyclerView recyclerView;
        RecyclerView.Adapter adapter;
        RecyclerView.LayoutManager layoutManager;
        sgbd.open();
        initDonnees();
        TraitementJSON tjs = new TraitementJSON(this);

        adapter = new PlaylistAdapter(lesPlaylists);
        recyclerView = findViewById(R.id.rcvVoirPlaylists);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        sgbd.close();

        EditText etIdentifiant = findViewById(R.id.etIdentifiant);
        EditText etTitrePlaylist = findViewById(R.id.etTitrePlaylist);
        Button btnCreerPlaylist = findViewById(R.id.buttoncreerplaylist);
        btnCreerPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lId = etIdentifiant.getText().toString();
                String leTitrePlaylist = etTitrePlaylist.getText().toString();
                Log.i("Recup","lId : "+lId+"\nle titre de la playlist : "+leTitrePlaylist);
                Toast.makeText( getApplicationContext(),
                        "Votre identité : "+lId,
                        Toast.LENGTH_LONG).show();
                Intent laSuite = new Intent(view.getContext(), VoirPlaylistsActivity.class);
                laSuite.putExtra("lId",lId);
                laSuite.putExtra("leTitrePlaylist",leTitrePlaylist);
                startActivity(laSuite);
            }
        });
    }

    private void initDonnees() {
        Log.i("initDonnees : ","testBase : "+sgbd.testBase());
        if(sgbd.testBase()==0) {
            String fichier = lectureFichierLocal();
            Log.i("initDonnees : ","recup fichier : "+fichier.toString());
            recPlaylists(fichier);
        }
        else {
            lesPlaylists = sgbd.donneLesPlaylists();
            Log.i("initDonnees : ","recup select : "+lesPlaylists.toString());
        }
        sgbd.close();
    }

    private String lectureFichierLocal() {
        StringBuilder builder = new StringBuilder();
        String ligne = null;
        BufferedReader br = null;
        br = new BufferedReader(
                new InputStreamReader(getResources().openRawResource(R.raw.lesplaylists)
                )
        );
        Log.i("Fichier","le fichier : "+br.toString());

        try {
            while ((ligne = br.readLine()) != null) {
                builder.append(ligne).append("\n");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    private void recPlaylists(String fichier) {
        JSONArray lesPlaylistsJSA = null;
        JSONObject jsPlaylist = null;
        String lId, leTitrePlaylist;
        Playlist playlist;
        JSONObject jObj = null;
        if (fichier != null) {
            try {
                jObj = new JSONObject(fichier);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        try {
            lesPlaylistsJSA = jObj.getJSONArray("lesPlaylists");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < lesPlaylistsJSA.length(); i++) {
            try {
                jsPlaylist = (JSONObject) lesPlaylistsJSA.get(i);
                lId = jsPlaylist.getString("id");Log.i("recPlaylist","nouveau ->"+lId);
                leTitrePlaylist = jsPlaylist.getString("titreplay");
                playlist = new Playlist(lId,leTitrePlaylist);
                Log.i("recPlaylist : ","recupérer la playlist : "+playlist.toString());
                lesPlaylists.add(playlist);
                sgbd.ajoutePlaylist(playlist);
                Log.i("recPlaylist : ","-> ArrayList : "+lesPlaylists.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}