package com.example.projetvegasmusique.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.projetvegasmusique.metier.Playlist;

import java.util.ArrayList;
import java.util.List;

public class GestionBDD {

    private SQLiteDatabase maxBase ;
    private BDHelper bdHelper;

    public GestionBDD(Context context) {
        this.bdHelper = new BDHelper(context, "vegasmusique", null, 1);
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
}
