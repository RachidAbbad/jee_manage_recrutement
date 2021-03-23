package com.models;

import javax.persistence.*;

@Entity
@Table(name="projet")
public class Projet {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "titre")
    private String titre;

    @Column(name = "type")
    private String type;

    @Column(name = "id_cv")
    private int idCv;

    public Projet(String titre, String type, int idCv) {
        this.titre = titre;
        this.type = type;
        this.idCv = idCv;
    }

    public Projet() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIdCv() {
        return idCv;
    }

    public void setIdCv(int idCv) {
        this.idCv = idCv;
    }
}
