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

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "id_cv")
    private int idCv;

    public Experience(String nomEntreprise, String sujet, String startDate, String endDate, int idCv) {
        this.nomEntreprise = nomEntreprise;
        this.sujet = sujet;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getIdCv() {
        return idCv;
    }

    public void setIdCv(int idCv) {
        this.idCv = idCv;
    }
}
