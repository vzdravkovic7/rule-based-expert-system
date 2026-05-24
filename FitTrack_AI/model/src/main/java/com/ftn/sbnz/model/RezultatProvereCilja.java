package com.ftn.sbnz.model;

import java.util.ArrayList;
import java.util.List;

public class RezultatProvereCilja {
    private Long korisnikId;
    private boolean planJeDovoljan;
    private int procenjenoNedelja;
    private List<String> kontraindikacije = new ArrayList<>();

    public RezultatProvereCilja() {
    }

    public RezultatProvereCilja(Long korisnikId) {
        this.korisnikId = korisnikId;
        this.planJeDovoljan = true;
    }

    public void dodajKontraindikaciju(String k) {
        this.kontraindikacije.add(k);
    }

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    public boolean isPlanJeDovoljan() {
        return planJeDovoljan;
    }

    public void setPlanJeDovoljan(boolean planJeDovoljan) {
        this.planJeDovoljan = planJeDovoljan;
    }

    public int getProcenjenoNedelja() {
        return procenjenoNedelja;
    }

    public void setProcenjenoNedelja(int procenjenoNedelja) {
        this.procenjenoNedelja = procenjenoNedelja;
    }

    public List<String> getKontraindikacije() {
        return kontraindikacije;
    }

    public void setKontraindikacije(List<String> kontraindikacije) {
        this.kontraindikacije = kontraindikacije;
    }
}