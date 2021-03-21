package com.models;

import javax.persistence.*;

@Entity
@Table(name="candidat")
public class Candidat {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "id_compte")
    private int idCompte;

    @Column(name = "nom_complet")
    private String nomComplet;

    @Column(name = "titre_emploi")
    private String titreEmploi;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "civilite")
    private String civilite;

    public Candidat(int idCompte, String nomComplet, String titreEmploi, String photoUrl, String civilite) {
        this.idCompte = idCompte;
        this.nomComplet = nomComplet;
        this.titreEmploi = titreEmploi;
        this.photoUrl = photoUrl;
        this.civilite = civilite;
    }

    public Candidat() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getTitreEmploi() {
        return titreEmploi;
    }

    public void setTitreEmploi(String titreEmploi) {
        this.titreEmploi = titreEmploi;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }
}
