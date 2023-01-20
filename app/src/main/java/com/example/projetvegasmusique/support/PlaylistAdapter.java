package com.example.projetvegasmusique.support;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetvegasmusique.R;
import com.example.projetvegasmusique.metier.Playlist;

import java.util.ArrayList;
import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter {

    List<Playlist> lesPlaylists = new ArrayList<>();

    public PlaylistAdapter(List<Playlist> lesPlaylists) {
        this.lesPlaylists = lesPlaylists;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //View view = inflater.inflate(R.layout.playlist, parent, false);
        View view = inflater.inflate(R.layout.playlist, parent, false);
        return new PlaylistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String lesInfos = String.valueOf(position)+"-"+String.valueOf(lesPlaylists.get(position));
        Playlist laPlaylist = lesPlaylists.get(position);
        ((PlaylistViewHolder)holder).remplirViewHolder(laPlaylist, position);
    }

    @Override
    public int getItemCount() {
        if(lesPlaylists != null) {
            return lesPlaylists.size();
        }
        else
        return 0;
    }
}
