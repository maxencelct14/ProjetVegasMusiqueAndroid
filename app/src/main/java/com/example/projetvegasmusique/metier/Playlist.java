package com.example.projetvegasmusique.metier;

public class Playlist {
    private String id;
    // id d'une playlist
    private String titreplay;
    //titre d'une playlist

    // Les méthodes Getter et Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitreplay() {
        return titreplay;
    }

    public void setTitreplay(String titreplay) {
        this.titreplay = titreplay;
    }

    // Constructeur de la classe Playlist
    public Playlist(String id, String titreplay) {
        this.id = id;
        this.titreplay = titreplay;
    }

    // Méthode qui affiche la playlist
    @Override
    public String toString() {
        return "Playlist{" +
                "id='" + id + '\'' +
                ", titreplay='" + titreplay + '\'' +
                '}';
    }
}
