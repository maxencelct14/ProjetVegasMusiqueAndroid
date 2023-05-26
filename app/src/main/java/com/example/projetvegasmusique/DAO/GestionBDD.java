package com.example.projetvegasmusique.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.projetvegasmusique.metier.Playlist;
import com.example.projetvegasmusique.metier.Artiste;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestionBDD {

    private SQLiteDatabase maxBase ;
    private final BDHelper bdHelper;

    public GestionBDD(Context context) {
        this.bdHelper = new BDHelper(context, "projetvegasmusique", null, 1);
    }

    //méthodes de gestion des données (CRUD)
    public void open() {
        maxBase = bdHelper.getWritableDatabase();
    }

    public void close() {
        maxBase.close();
    }

    public void ajoutePlaylist(Playlist playlist) {
        Log.i("sgbd","recupérer les playlists : "+playlist.toString());
        ContentValues cv = new ContentValues();
        cv.put("id", playlist.getId());
        cv.put("titreplay", playlist.getTitreplay());
        maxBase.insert("playlist", null, cv);
    }

    public ArrayList<String> donneLesTitresDesPlaylists() {
        ArrayList<String> lesTitres = new ArrayList<String>();
        String req = "select titreplay from playlist";
        Cursor cursor = maxBase.rawQuery(req, null, null);
        while (cursor.moveToNext()) {
            lesTitres.add(cursor.getString(0));
        }
        return lesTitres;
    }

    public ArrayList<Playlist> donneLesPlaylists() {
        ArrayList<Playlist> lesPlaylists = new ArrayList<Playlist>();
        String req = "select * from playlist order by titreplay";
        Playlist unePlaylist;
        String lId, leTitrePlaylist;
        Cursor cursor = maxBase.rawQuery(req, null, null);
        Log.i("sgbd"," nombre de playlist : "+cursor.getCount());
        while (cursor.moveToNext()) {
            lId = cursor.getString(0);
            leTitrePlaylist = cursor.getString(1);
            unePlaylist = new Playlist(lId,leTitrePlaylist);
            Log.i("sgbd","select playlist : "+unePlaylist.toString());
            lesPlaylists.add(unePlaylist);
        }
        Log.i("sgbd","playlist fini : "+lesPlaylists.toString());
        return lesPlaylists;
    }

    public void videPlaylist() {
        maxBase.delete("playlist", null, null);
    }

    public int testBase() {
        String req = "select count(titreplay) from playlist";
        Cursor cursor = maxBase.rawQuery(req, null, null);
        cursor.moveToNext();
        Log.i("sgbd"," test base version2 : "+cursor.getInt(0));
        return cursor.getInt(0);
    }

    public ArrayList<String> donneLesNomsDesArtistes() {
        ArrayList<String> lesNoms = new ArrayList<String>();
        String req = "select name from artiste"; // Changé 'nom' à 'name'
        Cursor cursor = maxBase.rawQuery(req, null);
        while (cursor.moveToNext()) {
            lesNoms.add(cursor.getString(0));
        }
        cursor.close();
        return lesNoms;
    }

    public List<Artiste> donneLesArtistes() {
        List<Artiste> lesArtistes = new ArrayList<>();
        String req = "select * from artiste";
        Cursor cursor = maxBase.rawQuery(req, null);
        while (cursor.moveToNext()) {
            Number id = cursor.getLong(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2); // Ajout de la description
            Date dateCrea = new Date(cursor.getLong(3));
            Date dateUpdate = new Date(cursor.getLong(4));

            lesArtistes.add(new Artiste(id, name, description, dateCrea, dateUpdate)); // Ajout de la description
        }
        cursor.close();
        return lesArtistes;
    }

    public List<String> donneLesMusiquesParArtiste(Number artisteId) {
        List<String> lesMusiques = new ArrayList<>();
        String req = "select name from musique where artiste_id = ?";
        Cursor cursor = maxBase.rawQuery(req, new String[]{artisteId.toString()});
        while (cursor.moveToNext()) {
            lesMusiques.add(cursor.getString(0));
        }
        cursor.close();
        return lesMusiques;
    }
}
