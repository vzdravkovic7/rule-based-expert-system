package com.ftn.sbnz.model;

import java.util.ArrayList;
import java.util.List;

public class TreningPlan {
    private Long korisnikId;
    private int kardioDani;
    private int snagaDani;
    private int serijeSnaga;
    private String ponavljanjaSnaga;
    private int odmorSekundi;
    private int kardioMinuta;
    private boolean povecanaTezina = false;
    private List<String> selektovaneVezbe = new ArrayList<>();

    public TreningPlan() {
    }

    public TreningPlan(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    public void dodajVezbu(String vezba) {
        this.selektovaneVezbe.add(vezba);
    }

    public boolean isPovecanaTezina() {
        return povecanaTezina;
    }

    public void setPovecanaTezina(boolean povecanaTezina) {
        this.povecanaTezina = povecanaTezina;
    }

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    public int getKardioDani() {
        return kardioDani;
    }

    public void setKardioDani(int kardioDani) {
        this.kardioDani = kardioDani;
    }

    public int getSnagaDani() {
        return snagaDani;
    }

    public void setSnagaDani(int snagaDani) {
        this.snagaDani = snagaDani;
    }

    public int getSerijeSnaga() {
        return serijeSnaga;
    }

    public void setSerijeSnaga(int serijeSnaga) {
        this.serijeSnaga = serijeSnaga;
    }

    public String getPonavljanjaSnaga() {
        return ponavljanjaSnaga;
    }

    public void setPonavljanjaSnaga(String ponavljanjaSnaga) {
        this.ponavljanjaSnaga = ponavljanjaSnaga;
    }

    public int getOdmorSekundi() {
        return odmorSekundi;
    }

    public void setOdmorSekundi(int odmorSekundi) {
        this.odmorSekundi = odmorSekundi;
    }

    public int getKardioMinuta() {
        return kardioMinuta;
    }

    public void setKardioMinuta(int kardioMinuta) {
        this.kardioMinuta = kardioMinuta;
    }

    public List<String> getSelektovaneVezbe() {
        return selektovaneVezbe;
    }

    public void setSelektovaneVezbe(List<String> selektovaneVezbe) {
        this.selektovaneVezbe = selektovaneVezbe;
    }
}