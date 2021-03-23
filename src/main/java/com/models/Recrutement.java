package com.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="recrutement")
public class Recrutement {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "id_recruteur")
    private int idRecruteur;

    @Column(name = "id_candidat")
    private int idCandidat;

    public Recrutement(Date date, int idRecruteur, int idCandidat) {
        this.date = date;
        this.idRecruteur = idRecruteur;
        this.idCandidat = idCandidat;
    }

    public Recrutement() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdRecruteur() {
        return idRecruteur;
    }

    public void setIdRecruteur(int idRecruteur) {
        this.idRecruteur = idRecruteur;
    }

    public int getIdCandidat() {
        return idCandidat;
    }

    public void setIdCandidat(int idCandidat) {
        this.idCandidat = idCandidat;
    }
}
