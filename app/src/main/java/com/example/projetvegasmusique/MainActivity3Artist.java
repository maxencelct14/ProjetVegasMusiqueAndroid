package com.example.projetvegasmusique;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.projetvegasmusique.DAO.GestionBDD;
import com.example.projetvegasmusique.metier.Artiste;

import java.util.List;

public class MainActivity3Artist extends AppCompatActivity {

    private TextView mDataTextView;
    private GestionBDD BDHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity3_artist);

        mDataTextView = findViewById(R.id.tvlartist);
        BDHelper = new GestionBDD(this);
        BDHelper.open();

        List<Artiste> lesArtistes = BDHelper.donneLesArtistes();
        StringBuilder data = new StringBuilder();
        for (Artiste art : lesArtistes) {
            data.append(art.toString()).append("\n");
            List<String> lesMusiques = BDHelper.donneLesMusiquesParArtiste(art.getId()); // Ajout des musiques de l'artiste
            for (String musique : lesMusiques) {
                data.append("\t").append(musique).append("\n"); // Ajout des noms des musiques à l'affichage
            }
            data.append("\n"); // Ajout d'un saut de ligne entre chaque artiste
        }
        mDataTextView.setText(data.toString());

        BDHelper.close();
    }
}
