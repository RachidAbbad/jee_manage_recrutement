package com.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="experience")
public class Experience {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "nom_entreprise")
    private String nomEntreprise;

    @Column(name = "sujet")
    private String sujet;

    @Column(name = "type")
    private String type;

    @Column(name = "duree")
    private Date duree;

    @Column(name = "id_cv")
    private int idCv;

    public Experience(String nomEntreprise, String sujet, String type, Date duree, int idCv) {
        this.nomEntreprise = nomEntreprise;
        this.sujet = sujet;
        this.type = type;
        this.duree = duree;
        this.idCv = idCv;
    }

    public Experience() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDuree() {
        return duree;
    }

    public void setDuree(Date duree) {
        this.duree = duree;
    }

    public int getIdCv() {
        return idCv;
    }

    public void setIdCv(int idCv) {
        this.idCv = idCv;
    }
}
