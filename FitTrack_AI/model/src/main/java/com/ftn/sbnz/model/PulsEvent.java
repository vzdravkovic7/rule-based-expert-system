package com.ftn.sbnz.model;

import java.io.Serializable;
import java.util.Date;

public class PulsEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long korisnikId;
    private int bpm;
    private Date timestamp;
    private long redniBroj;

    public PulsEvent() {
    }

    public PulsEvent(Long korisnikId, int bpm, Date timestamp, long redniBroj) {
        this.korisnikId = korisnikId;
        this.bpm = bpm;
        this.timestamp = timestamp;
        this.redniBroj = redniBroj;
    }

    public Long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Long korisnikId) {
        this.korisnikId = korisnikId;
    }

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public long getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(long redniBroj) {
        this.redniBroj = redniBroj;
    }
}