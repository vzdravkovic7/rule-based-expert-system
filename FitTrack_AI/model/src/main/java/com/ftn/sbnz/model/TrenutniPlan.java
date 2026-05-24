package com.ftn.sbnz.model;

public class TrenutniPlan {
    private Long korisnikId;
    private int kalorijskiDeficit;
    private String tipTreninga;
    private String nivoAktivnosti;
    private double dnevniProtein;
    private double dnevneMasti;
    private double dnevniUgljeniHidrati;
    private double promenaMasePoslednjihNedelja;

    public TrenutniPlan() {
    }

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    public int getKalorijskiDeficit() {
        return kalorijskiDeficit;
    }

    public void setKalorijskiDeficit(int kalorijskiDeficit) {
        this.kalorijskiDeficit = kalorijskiDeficit;
    }

    public String getTipTreninga() {
        return tipTreninga;
    }

    public void setTipTreninga(String tipTreninga) {
        this.tipTreninga = tipTreninga;
    }

    public String getNivoAktivnosti() {
        return nivoAktivnosti;
    }

    public void setNivoAktivnosti(String nivoAktivnosti) {
        this.nivoAktivnosti = nivoAktivnosti;
    }

    public double getDnevniProtein() {
        return dnevniProtein;
    }

    public void setDnevniProtein(double dnevniProtein) {
        this.dnevniProtein = dnevniProtein;
    }

    public double getDnevneMasti() {
        return dnevneMasti;
    }

    public void setDnevneMasti(double dnevneMasti) {
        this.dnevneMasti = dnevneMasti;
    }

    public double getDnevniUgljeniHidrati() {
        return dnevniUgljeniHidrati;
    }

    public void setDnevniUgljeniHidrati(double dnevniUgljeniHidrati) {
        this.dnevniUgljeniHidrati = dnevniUgljeniHidrati;
    }

    public double getPromenaMasePoslednjihNedelja() {
        return promenaMasePoslednjihNedelja;
    }

    public void setPromenaMasePoslednjihNedelja(double promenaMasePoslednjihNedelja) {
        this.promenaMasePoslednjihNedelja = promenaMasePoslednjihNedelja;
    }
}