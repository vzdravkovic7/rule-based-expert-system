package com.ftn.sbnz.model;

public class TreningIntenzitet {
    private Long korisnikId;
    private String intenzitet;

    public TreningIntenzitet() {}
    public TreningIntenzitet(Long korisnikId, String intenzitet) {
        this.korisnikId = korisnikId;
        this.intenzitet = intenzitet;
    }

    public Long getKorisnikId() { return korisnikId; }
    public void setKorisnikId(Long korisnikId) { this.korisnikId = korisnikId; }
    public String getIntenzitet() { return intenzitet; }
    public void setIntenzitet(String intenzitet) { this.intenzitet = intenzitet; }
}