package com.models;

import javax.persistence.*;

@Entity
@Table(name="recruteur")
public class Recruteur {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "id_compte")
    private int idCompte;

    @Column(name = "nom")
    private String nom;

    @Column(name = "description")
    private String description;

    @Column(name = "siteweb")
    private String siteweb;

    @Column(name = "logo_url")
    private String logoUrl;

    public Recruteur(int idCompte, String nom, String description, String siteweb, String logoUrl) {
        this.idCompte = idCompte;
        this.nom = nom;
        this.description = description;
        this.siteweb = siteweb;
        this.logoUrl = logoUrl;
    }

    public Recruteur() {

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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSiteweb() {
        return siteweb;
    }

    public void setSiteweb(String siteweb) {
        this.siteweb = siteweb;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
