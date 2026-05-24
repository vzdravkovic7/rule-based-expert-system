package com.ftn.sbnz.model;

public class CiljKorisnika {
    private Long korisnikId;
    private String tipCilja;
    private double ciljniProcenatMasti;

    public CiljKorisnika() {
    }

    public CiljKorisnika(Long korisnikId, String tipCilja, double ciljniProcenatMasti) {
        this.korisnikId = korisnikId;
        this.tipCilja = tipCilja;
        this.ciljniProcenatMasti = ciljniProcenatMasti;
    }

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
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
}