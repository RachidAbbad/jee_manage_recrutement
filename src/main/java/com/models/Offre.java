package com.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="offre")
public class Offre {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "titre")
    private String titre;

    @Column(name = "description")
    private String description;

    @Column(name = "emplacement")
    private String emplacement;

    @Column(name = "type_contrat")
    private String typeContrat;

    @Column(name = "metier")
    private String metier;

    @Column(name = "salaire_et_primes")
    private String salairePrimes;

    @Column(name = "date_creation")
    private Date dateCreation;

    @Column(name = "competences_requises")
    private String competencesRequises;

    @Column(name = "id_recruteur")
    private int idRecruteur;

    @Column(name = "id_departement")
    private int idDepartement;

    public Offre(String titre, String description, String emplacement, String typeContrat, String metier, String salairePrimes, Date dateCreation, String competencesRequises, int idRecruteur, int idDepartement) {
        this.titre = titre;
        this.description = description;
        this.emplacement = emplacement;
        this.typeContrat = typeContrat;
        this.metier = metier;
        this.salairePrimes = salairePrimes;
        this.dateCreation = dateCreation;
        this.competencesRequises = competencesRequises;
        this.idRecruteur = idRecruteur;
        this.idDepartement = idDepartement;
    }

    public Offre() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public String getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(String typeContrat) {
        this.typeContrat = typeContrat;
    }

    public String getMetier() {
        return metier;
    }

    public void setMetier(String metier) {
        this.metier = metier;
    }

    public String getSalairePrimes() {
        return salairePrimes;
    }

    public void setSalairePrimes(String salairePrimes) {
        this.salairePrimes = salairePrimes;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getCompetencesRequises() {
        return competencesRequises;
    }

    public void setCompetencesRequises(String competencesRequises) {
        this.competencesRequises = competencesRequises;
    }

    public int getIdRecruteur() {
        return idRecruteur;
    }

    public void setIdRecruteur(int idRecruteur) {
        this.idRecruteur = idRecruteur;
    }

    public int getIdDepartement() {
        return idDepartement;
    }

    public void setIdDepartement(int idDepartement) {
        this.idDepartement = idDepartement;
    }
}
