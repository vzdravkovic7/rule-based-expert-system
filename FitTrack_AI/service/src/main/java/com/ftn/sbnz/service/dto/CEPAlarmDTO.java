package com.ftn.sbnz.service.dto;

public class CEPAlarmDTO {
    public String nazivPravila;
    public String nivo;
    public String poruka;

    public CEPAlarmDTO() {
    }

    public CEPAlarmDTO(String nazivPravila, String nivo, String poruka) {
        this.nazivPravila = nazivPravila;
        this.nivo = nivo;
        this.poruka = poruka;
    }
}