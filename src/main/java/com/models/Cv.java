package com.models;

import javax.persistence.*;

@Entity
@Table(name="cv")
public class Cv {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "id_candidat")
    private int idCandidat;

    public Cv(String description, int idCandidat) {
        this.description = description;
        this.idCandidat = idCandidat;
    }

    public Cv() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdCandidat() {
        return idCandidat;
    }

    public void setIdCandidat(int idCandidat) {
        this.idCandidat = idCandidat;
    }
}
