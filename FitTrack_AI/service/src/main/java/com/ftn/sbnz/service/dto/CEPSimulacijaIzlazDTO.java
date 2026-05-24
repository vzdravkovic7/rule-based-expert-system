package com.ftn.sbnz.service.dto;

import java.util.List;

public class CEPSimulacijaIzlazDTO {
    public int izracunatMaxPuls;
    public int ciljnaZonaDonja;
    public int ciljnaZonaGornja;
    public List<CEPAlarmDTO> aktiviraniAlarmi;

    public CEPSimulacijaIzlazDTO() {
    }

    public CEPSimulacijaIzlazDTO(int izracunatMaxPuls, int ciljnaZonaDonja, int ciljnaZonaGornja,
            List<CEPAlarmDTO> aktiviraniAlarmi) {
        this.izracunatMaxPuls = izracunatMaxPuls;
        this.ciljnaZonaDonja = ciljnaZonaDonja;
        this.ciljnaZonaGornja = ciljnaZonaGornja;
        this.aktiviraniAlarmi = aktiviraniAlarmi;
    }
}