package com.models;

import javax.persistence.*;

@Entity
@Table(name="compte")
public class Compte {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "ville")
    private String ville;

    @Column(name = "email")
    private String email;

    @Column(name = "mot_de_passe")
    private String moteDePasse;

    @Column(name = "num_tel")
    private String numTel;

    @Column(name = "verifed")
    private int verified;

    @Column(name = "type_compte")
    private String typeCompte;

    public Compte(String ville, String email, String moteDePasse, String numTel, int verified, String typeCompte) {
        this.ville = ville;
        this.email = email;
        this.moteDePasse = moteDePasse;
        this.numTel = numTel;
        this.verified = verified;
        this.typeCompte = typeCompte;
    }

    public Compte() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMoteDePasse() {
        return moteDePasse;
    }

    public void setMoteDePasse(String moteDePasse) {
        this.moteDePasse = moteDePasse;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public int getVerified() {
        return verified;
    }

    public void setVerified(int verified) {
        this.verified = verified;
    }

    public String getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(String typeCompte) {
        this.typeCompte = typeCompte;
    }
}
