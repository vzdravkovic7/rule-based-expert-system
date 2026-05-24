package com.ftn.sbnz.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vezba {
    @Id
    private String naziv;
    private String javaVrsta;
    private String intenzitet;
    private boolean kontraindikovanaZaPritisak;

    public Vezba() {
    }

    public Vezba(String naziv, String javaVrsta, String intenzitet, boolean kontraindikovanaZaPritisak) {
        this.naziv = naziv;
        this.javaVrsta = javaVrsta;
        this.intenzitet = intenzitet;
        this.kontraindikovanaZaPritisak = kontraindikovanaZaPritisak;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getJavaVrsta() {
        return javaVrsta;
    }

    public void setJavaVrsta(String javaVrsta) {
        this.javaVrsta = javaVrsta;
    }

    public String getIntenzitet() {
        return intenzitet;
    }

    public void setIntenzitet(String intenzitet) {
        this.intenzitet = intenzitet;
    }

    public boolean isKontraindikovanaZaPritisak() {
        return kontraindikovanaZaPritisak;
    }

    public void setKontraindikovanaZaPritisak(boolean kontraindikovanaZaPritisak) {
        this.kontraindikovanaZaPritisak = kontraindikovanaZaPritisak;
    }
}