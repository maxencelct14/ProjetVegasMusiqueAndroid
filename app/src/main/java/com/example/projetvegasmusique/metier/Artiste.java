package com.example.projetvegasmusique.metier;

import java.util.Date;

public class Artiste {
    private Number id;
    private String nom;
    private String description; // nouvel attribut pour la description
    private Date dateCrea;
    private Date dateupdate;

    public Artiste(Number id, String nom, String description, Date dateCrea, Date dateupdate) {
        this.id = id;
        this.nom = nom;
        this.description = description; // initialiser la description
        this.dateCrea = dateCrea;
        this.dateupdate = dateupdate;
    }

    public String getDescription() { return description; } // nouveau getter pour la description

    public Number getId() { return id; }

    public String getNom() {
        return nom;
    }

    public Date getDateCrea() {
        return dateCrea;
    }

    public Date getDateupdate() { return dateupdate; }

    @Override
    public String toString() {
        return id + " " + " - " + nom + " "+ " - " +
                " Créer le =" + dateCrea.getDay()+"/"+ dateCrea.getMonth()+"/"+dateCrea.getYear()
                + " " + " - " + dateupdate.getDay()+"/"+ dateupdate.getMonth()+"/"+dateupdate.getYear()
                ;
    }
}
