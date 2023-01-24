package com.example.projetvegasmusique.support;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetvegasmusique.R;
import com.example.projetvegasmusique.metier.Playlist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TraitementJSON extends AsyncTask {

    private List<Playlist> lesPlaylists = new ArrayList<Playlist>();
    Context context;
    JSONObject jObj = null;
    URL url;
    HttpURLConnection connexion;
    //GestionBD sgbd;

    // constructeur
    public TraitementJSON(Context context) {
        this.context = context;
        //sgbd = new GestionBD(context);
    }

    @Override
    protected Boolean doInBackground(Object... urls) {

        //sgbd.open();;
        try {
            url = new URL((String) urls[0]);
            Log.i("doInBack", "L'url " + urls[0]);
            //String fichier = lectureFichierDistant();
            String fichier = lectureFichierLocal();
            //sgbd.videPersonnage();
            recTitres(fichier);
            Log.i("doInBack", "Le fichier distant/local : " + fichier);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //sgbd.close();;
        return true;
    }
    private String lectureFichierLocal() {
        StringBuilder builder = new StringBuilder();
        String ligne = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(
                    new FileReader(String.valueOf(R.raw.lesplaylists))
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private String lectureFichierDistant() {
        StringBuilder builder = new StringBuilder();
        try {
            connexion = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String ligne = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(
                    new InputStreamReader(connexion.getInputStream())
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private void recTitres(String fichier) {

        JSONArray lesPlaylistsJSA = null;
        JSONObject jsPlaylist = null;
        String lId, leTitrePlaylist;
        Playlist playlist;

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
                lesPlaylists.add(playlist);
                //sgbd.ajoutePlaylist(playlist);
                Log.i("doInBack : ","recPlaylist -> ArrayList : "+lesPlaylists.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        List<String> leTitrePlaylist = new ArrayList<String>();
        leTitrePlaylist.addAll(Arrays.asList(lesPlaylists.toString()));
        RecyclerView.Adapter adapter;
        adapter = new PlaylistAdapter(lesPlaylists);
        RecyclerView recyclerView = ((AppCompatActivity)context).findViewById(R.id.rcvVoirPlaylists);
        recyclerView.setAdapter(adapter);
    }

}
