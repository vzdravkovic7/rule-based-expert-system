package com.ftn.sbnz.service.dto;

import java.time.LocalDate;
import java.util.List;

public class UnosTreningaDTO {
    public Long korisnikId;
    public List<LogStavkaDTO> logovi;

    public static class LogStavkaDTO {
        public int rpe;
        public LocalDate datum;
    }
}