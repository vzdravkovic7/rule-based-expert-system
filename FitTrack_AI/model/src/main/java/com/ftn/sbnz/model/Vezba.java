package com.ftn.sbnz.model;

public class Vezba {
    private String naziv;
    private String vrsta;
    private String intenzitet;
    private boolean kontraindikovanaZaPritisak;

    public Vezba() {
    }

    public Vezba(String naziv, String vrsta, String intenzitet, boolean kontraindikovanaZaPritisak) {
        this.naziv = naziv;
        this.vrsta = vrsta;
        this.intenzitet = intenzitet;
        this.kontraindikovanaZaPritisak = kontraindikovanaZaPritisak;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getVrsta() {
        return vrsta;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
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