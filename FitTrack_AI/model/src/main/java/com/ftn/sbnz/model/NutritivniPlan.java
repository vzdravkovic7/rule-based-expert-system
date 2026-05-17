package com.ftn.sbnz.model;

public class NutritivniPlan {
    private Long korisnikId;
    private double bmr;
    private double tdee;
    private double ciljaneKalorije;
    private double proteiniGrama;
    private double mastiGrama;
    private double ugljeniHidratiGrama;

    public NutritivniPlan() {
    }

    public NutritivniPlan(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    public double getBmr() {
        return bmr;
    }

    public void setBmr(double bmr) {
        this.bmr = bmr;
    }

    public double getTdee() {
        return tdee;
    }

    public void setTdee(double tdee) {
        this.tdee = tdee;
    }

    public double getCiljaneKalorije() {
        return ciljaneKalorije;
    }

    public void setCiljaneKalorije(double ciljaneKalorije) {
        this.ciljaneKalorije = ciljaneKalorije;
    }

    public double getProteiniGrama() {
        return proteiniGrama;
    }

    public void setProteiniGrama(double proteiniGrama) {
        this.proteiniGrama = proteiniGrama;
    }

    public double getMastiGrama() {
        return mastiGrama;
    }

    public void setMastiGrama(double mastiGrama) {
        this.mastiGrama = mastiGrama;
    }

    public double getUgljeniHidratiGrama() {
        return ugljeniHidratiGrama;
    }

    public void setUgljeniHidratiGrama(double ugljeniHidratiGrama) {
        this.ugljeniHidratiGrama = ugljeniHidratiGrama;
    }
}