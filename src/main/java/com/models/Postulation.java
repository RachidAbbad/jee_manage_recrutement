package com.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="postulation")
public class Postulation {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "body")
    private String body;

    @Column(name = "id_candidat")
    private int idCandidat;

    @Column(name = "id_offre")
    private int idOffre;

    public Postulation(int idCandidat, int idOffre, Date date, String body) {
        this.date = date;
        this.idCandidat = idCandidat;
        this.idOffre = idOffre;
        this.body = body;
    }

    public Postulation() {

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

    public int getIdCandidat() {
        return idCandidat;
    }

    public void setIdCandidat(int idCandidat) {
        this.idCandidat = idCandidat;
    }

    public int getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(int idOffre) {
        this.idOffre = idOffre;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
