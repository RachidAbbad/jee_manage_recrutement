package com.models;

import javax.persistence.*;

@Entity
@Table(name="departement")
public class Departement {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "icon")
    private String icon;

    public Departement(String nom, String icon) {
        this.nom = nom;
        this.icon = icon;
    }

    public Departement() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
