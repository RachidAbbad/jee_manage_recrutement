package com.models;

import javax.persistence.*;

@Entity
@Table(name="competence")
public class Competence {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "niveau")
    private String niveau;

    @Column(name = "id_cv")
    private int idCv;

    public Competence(String nom, String niveau, int idCv) {
        this.nom = nom;
        this.niveau = niveau;
        this.idCv = idCv;
    }

    public Competence() {

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

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public int getIdCv() {
        return idCv;
    }

    public void setIdCv(int idCv) {
        this.idCv = idCv;
    }
}
