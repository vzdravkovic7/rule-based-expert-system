package com.ftn.sbnz.model;

public class ProgresijaNalog {
    private Long korisnikId;
    private String tip;

    public ProgresijaNalog() {
    }

    public ProgresijaNalog(Long korisnikId, String tip) {
        this.korisnikId = korisnikId;
        this.tip = tip;
    }

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}