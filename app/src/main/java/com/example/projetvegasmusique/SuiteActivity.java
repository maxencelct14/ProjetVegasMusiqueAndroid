package com.example.projetvegasmusique;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SuiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle extras = getIntent().getExtras();
        String laPlaylist = extras.getString("laPlaylist");
        int laPosition = extras.getInt("laPosition");
    }
}
