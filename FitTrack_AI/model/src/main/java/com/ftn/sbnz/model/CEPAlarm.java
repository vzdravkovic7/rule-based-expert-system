package com.ftn.sbnz.model;

import java.io.Serializable;
import java.util.Date;

public class CEPAlarm implements Serializable {
    private Long korisnikId;
    private String nazivPravila;
    private String nivo;
    private String poruka;
    private Date timestamp;

    public CEPAlarm() {
    }

    public CEPAlarm(Long korisnikId, String nazivPravila, String nivo, String poruka) {
        this.korisnikId = korisnikId;
        this.nazivPravila = nazivPravila;
        this.nivo = nivo;
        this.poruka = poruka;
        this.timestamp = new Date();
    }

    public Long getKorisnikId() {
        return korisnikId;
    }

    public String getNivio() {
        return nivo;
    }

    public String getNazivPravila() {
        return nazivPravila;
    }

    public String getPoruka() {
        return poruka;
    }
}