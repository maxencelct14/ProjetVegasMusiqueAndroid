package com.example.projetvegasmusique.support;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projetvegasmusique.R;
import com.example.projetvegasmusique.SuiteActivity;
import com.example.projetvegasmusique.metier.Playlist;

public class PlaylistViewHolder extends RecyclerView.ViewHolder {
    private final TextView tvPlaylist;
    public PlaylistViewHolder(View view) {
        super(view);
        tvPlaylist = (TextView) view.findViewById(R.id.tvPlaylist);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent laSuite = new Intent(view.getContext(), SuiteActivity.class);
                String lesInfos[]= tvPlaylist.getText().toString().split("-");
                int laPosition =  Integer.parseInt(lesInfos[0]);
                String laPlaylist = lesInfos[1];
                laSuite.putExtra("laPlaylist",laPlaylist);
                laSuite.putExtra("laPosition",laPosition);
                view.getContext().startActivity(laSuite);
            }
        });
    }

    public void remplirViewHolder(String laPlaylist) {
        String lesInfos[] = laPlaylist.split("-");
        String leTitrePlaylist = lesInfos[1];
        tvPlaylist.setText(leTitrePlaylist);
    }

    public void remplirViewHolder(Playlist laPlaylist, int position) {
        String leTitrePlaylist = laPlaylist.getId()+" "+laPlaylist.getTitreplay();
        Log.i("viewHolder","remplir (pos) : "+position);
        tvPlaylist.setText(position+" - "+leTitrePlaylist);
    }
}
