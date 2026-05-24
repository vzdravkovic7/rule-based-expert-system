package com.ftn.sbnz.service.dto;

public class BackwardZahtevDTO {
    private int godine;
    private double bmi;
    private String pritisakKategorija;

    private String tipCilja;
    private double ciljniProcenatMasti;

    private int kalorijskiDeficit;
    private String tipTreninga;
    private String nivoAktivnosti;
    private double dnevniProtein;
    private double promenaMasePoslednjihNedelja;

    public BackwardZahtevDTO() {
    }

    public int getGodine() {
        return godine;
    }

    public void setGodine(int godine) {
        this.godine = godine;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public String getPritisakKategorija() {
        return pritisakKategorija;
    }

    public void setPritisakKategorija(String pritisakKategorija) {
        this.pritisakKategorija = pritisakKategorija;
    }

    public String getTipCilja() {
        return tipCilja;
    }

    public void setTipCilja(String tipCilja) {
        this.tipCilja = tipCilja;
    }

    public double getCiljniProcenatMasti() {
        return ciljniProcenatMasti;
    }

    public void setCiljniProcenatMasti(double ciljniProcenatMasti) {
        this.ciljniProcenatMasti = ciljniProcenatMasti;
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

    public double getPromenaMasePoslednjihNedelja() {
        return promenaMasePoslednjihNedelja;
    }

    public void setPromenaMasePoslednjihNedelja(double promenaMasePoslednjihNedelja) {
        this.promenaMasePoslednjihNedelja = promenaMasePoslednjihNedelja;
    }
}