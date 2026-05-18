package com.ftn.sbnz.model;

public class Korisnik {
    private Long id;
    private String ime;
    private int godine;
    private double visina;
    private double telesnaMasa;
    private int sistolickiPritisak;
    private int dijastolickiPritisak;
    private double metMinutaNedeljno;
    private String cilj;
    private double dnevniUnosKalorija;
    private double dnevniUnosProteina;

    private String bmiKategorija;
    private String pritisakKategorija;
    private String nivoAktivnosti;

    public Korisnik() {
    }

    public Korisnik(Long id, String ime, int godine, double visina, double telesnaMasa,
            int sistolickiPritisak, int dijastolickiPritisak, double metMinutaNedeljno, String cilj) {
        this.id = id;
        this.ime = ime;
        this.godine = godine;
        this.visina = visina;
        this.telesnaMasa = telesnaMasa;
        this.sistolickiPritisak = sistolickiPritisak;
        this.dijastolickiPritisak = dijastolickiPritisak;
        this.metMinutaNedeljno = metMinutaNedeljno;
        this.cilj = cilj;
    }

    public double getBmi() {
        double visinaUMetrima = visina / 100.0;
        return telesnaMasa / (visinaUMetrima * visinaUMetrima);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getGodine() {
        return godine;
    }

    public void setGodine(int godine) {
        this.godine = godine;
    }

    public double getVisina() {
        return visina;
    }

    public void setVisina(double visina) {
        this.visina = visina;
    }

    public double getTelesnaMasa() {
        return telesnaMasa;
    }

    public void setTelesnaMasa(double telesnaMasa) {
        this.telesnaMasa = telesnaMasa;
    }

    public double getDnevniUnosKalorija() {
        return dnevniUnosKalorija;
    }

    public void setDnevniUnosKalorija(double dnevniUnosKalorija) {
        this.dnevniUnosKalorija = dnevniUnosKalorija;
    }

    public double getDnevniUnosProteina() {
        return dnevniUnosProteina;
    }

    public void setDnevniUnosProteina(double dnevniUnosProteina) {
        this.dnevniUnosProteina = dnevniUnosProteina;
    }

    public int getSistolickiPritisak() {
        return sistolickiPritisak;
    }

    public void setSistolickiPritisak(int sistolickiPritisak) {
        this.sistolickiPritisak = sistolickiPritisak;
    }

    public int getDijastolickiPritisak() {
        return dijastolickiPritisak;
    }

    public void setDijastolickiPritisak(int dijastolickiPritisak) {
        this.dijastolickiPritisak = dijastolickiPritisak;
    }

    public double getMetMinutaNedeljno() {
        return metMinutaNedeljno;
    }

    public void setMetMinutaNedeljno(double metMinutaNedeljno) {
        this.metMinutaNedeljno = metMinutaNedeljno;
    }

    public String getCilj() {
        return cilj;
    }

    public void setCilj(String cilj) {
        this.cilj = cilj;
    }

    public String getBmiKategorija() {
        return bmiKategorija;
    }

    public void setBmiKategorija(String bmiKategorija) {
        this.bmiKategorija = bmiKategorija;
    }

    public String getPritisakKategorija() {
        return pritisakKategorija;
    }

    public void setPritisakKategorija(String pritisakKategorija) {
        this.pritisakKategorija = pritisakKategorija;
    }

    public String getNivoAktivnosti() {
        return nivoAktivnosti;
    }

    public void setNivoAktivnosti(String nivoAktivnosti) {
        this.nivoAktivnosti = nivoAktivnosti;
    }
}