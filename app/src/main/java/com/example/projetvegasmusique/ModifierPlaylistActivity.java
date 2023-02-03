package com.example.projetvegasmusique;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetvegasmusique.DAO.GestionBDD;

public class ModifierPlaylistActivity extends AppCompatActivity {

    GestionBDD sgbd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_playlist);
        Bundle extras = getIntent().getExtras();
        String lId = extras.getString("lId");
        String leTitrePlaylist = extras.getString("leTitrePlaylist");

        Button btnModifierPlaylist= this.findViewById(R.id.btnModifierPlaylist) ;
        btnModifierPlaylist.setOnClickListener((View.OnClickListener) this);

        EditText txtId = this.findViewById(R.id.etIdentifiant);
        EditText txtTitrePlaylist = this.findViewById(R.id.etTitrePlaylist);

        txtId.setText(lId);
        txtTitrePlaylist.setText(leTitrePlaylist);

        //instanciation de la sgbd
        sgbd = new GestionBDD(this);
        //ouverture de la base de données
        sgbd.open();

    }

    }
