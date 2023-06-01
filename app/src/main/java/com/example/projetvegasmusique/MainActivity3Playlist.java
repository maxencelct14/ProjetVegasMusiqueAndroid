package com.example.projetvegasmusique;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity3Playlist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity3_playlist);
        Button button1 = findViewById(R.id.buttoncreerplaylist);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3Playlist.this, CreerPlaylistActivity.class);
                startActivity(intent);
            }
        });

        Button button2 = findViewById(R.id.buttonmodifierplaylist);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3Playlist.this, ModifierPlaylistActivity.class);
                startActivity(intent);
            }
        });

        Button button3 = findViewById(R.id.buttonvoirplaylist);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3Playlist.this, VoirPlaylistActivity.class);
                startActivity(intent);
            }
        });

        Button button4 = findViewById(R.id.buttonvoirplaylists);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3Playlist.this, VoirPlaylistsActivity.class);
                startActivity(intent);
            }
        });
    }
}
