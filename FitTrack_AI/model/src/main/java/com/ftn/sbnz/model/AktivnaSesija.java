package com.ftn.sbnz.model;

import java.io.Serializable;

public class AktivnaSesija implements Serializable {
    private Long korisnikId;
    private boolean aktivna;

    public AktivnaSesija() {
    }

    public AktivnaSesija(Long korisnikId, boolean aktivna) {
        this.korisnikId = korisnikId;
        this.aktivna = aktivna;
    }

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    public boolean isAktivna() {
        return aktivna;
    }

    public void setAktivna(boolean aktivna) {
        this.aktivna = aktivna;
    }
}