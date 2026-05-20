package com.ftn.sbnz.service.dto;

import java.util.List;

public class TreningPlanDTO {
    public int kardioDani;
    public int snagaDani;
    public int serijeSnaga;
    public String ponavljanjaSnaga;
    public int odmorSekundi;
    public int kardioMinuta;
    public List<String> selektovaneVezbe;

    public String bmiKategorija;
    public String pritisakKategorija;
    public String nivoAktivnosti;
    public String intenzitetTreninga;
    public String porukaProgresa;
    public boolean isSenior;

    public TreningPlanDTO() {
    }

    public TreningPlanDTO(int kardioDani, int snagaDani, int serijeSnaga, String ponavljanjaSnaga,
            int odmorSekundi, int kardioMinuta, List<String> selektovaneVezbe,
            String bmiKategorija, String pritisakKategorija, String nivoAktivnosti, String intenzitetTreninga) {
        this.kardioDani = kardioDani;
        this.snagaDani = snagaDani;
        this.serijeSnaga = serijeSnaga;
        this.ponavljanjaSnaga = ponavljanjaSnaga;
        this.odmorSekundi = odmorSekundi;
        this.kardioMinuta = kardioMinuta;
        this.selektovaneVezbe = selektovaneVezbe;
        this.bmiKategorija = bmiKategorija;
        this.pritisakKategorija = pritisakKategorija;
        this.nivoAktivnosti = nivoAktivnosti;
        this.intenzitetTreninga = intenzitetTreninga;
    }
}