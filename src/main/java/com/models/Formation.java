package com.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="formation")
public class Formation {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "nom_etablissement")
    private String nomEtablissement;

    @Column(name = "nom_diplome")
    private String nomDiplome;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "id_cv")
    private int idCv;

    public Formation(String nomEtablissement, String nomDiplome, String startDate, String endDate, int idCv) {
        this.nomEtablissement = nomEtablissement;
        this.nomDiplome = nomDiplome;
        this.startDate = startDate;
        this.endDate = endDate;
        this.idCv = idCv;
    }

    public Formation() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomEtablissement() {
        return nomEtablissement;
    }

    public void setNomEtablissement(String nomEtablissement) {
        this.nomEtablissement = nomEtablissement;
    }

    public String getNomDiplome() {
        return nomDiplome;
    }

    public void setNomDiplome(String nomDiplome) {
        this.nomDiplome = nomDiplome;
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
