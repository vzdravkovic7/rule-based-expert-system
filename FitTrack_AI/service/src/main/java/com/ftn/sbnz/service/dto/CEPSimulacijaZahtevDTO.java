package com.ftn.sbnz.service.dto;

import java.util.List;

public class CEPSimulacijaZahtevDTO {
    public Long korisnikId;
    public int godine;
    public int pulsUMirovanju;
    public String pritisakKategorija;
    public String intenzitetTreninga;
    public boolean aktivnaSesija;
    public List<PulsSimulacijaEventDTO> pulsniDogadjaji;

    public CEPSimulacijaZahtevDTO() {
    }
}