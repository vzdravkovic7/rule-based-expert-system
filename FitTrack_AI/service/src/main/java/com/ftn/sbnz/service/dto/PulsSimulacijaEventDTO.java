package com.ftn.sbnz.service.dto;

public class PulsSimulacijaEventDTO {
    public int bpm;
    public int sekundiOdPocetka;

    public PulsSimulacijaEventDTO() {
    }

    public PulsSimulacijaEventDTO(int bpm, int sekundiOdPocetka) {
        this.bpm = bpm;
        this.sekundiOdPocetka = sekundiOdPocetka;
    }
}