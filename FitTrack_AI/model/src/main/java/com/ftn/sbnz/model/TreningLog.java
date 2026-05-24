package com.ftn.sbnz.model;

import java.time.LocalDate;

public class TreningLog {
    private Long korisnikId;
    private int rpe;
    private LocalDate datum;
    private boolean obradjen = false;

    public TreningLog() {
    }

    public TreningLog(Long korisnikId, int rpe, LocalDate datum) {
        this.korisnikId = korisnikId;
        this.rpe = rpe;
        this.datum = datum;
        this.obradjen = false;
    }

    public boolean isObradjen() {
        return obradjen;
    }

    public void setObradjen(boolean obradjen) {
        this.obradjen = obradjen;
    }

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    public int getRpe() {
        return rpe;
    }

    public void setRpe(int rpe) {
        this.rpe = rpe;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
}