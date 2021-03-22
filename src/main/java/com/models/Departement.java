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

    public Departement(String nom) {
        this.nom = nom;
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
}
