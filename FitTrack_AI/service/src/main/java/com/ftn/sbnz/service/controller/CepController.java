package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.model.*;
import com.ftn.sbnz.service.dto.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/cep")
@CrossOrigin(origins = "http://localhost:4200")
public class CepController {

    @Autowired
    private KieContainer kieContainer;

    @PostMapping("/simuliraj")
    public ResponseEntity<CEPSimulacijaIzlazDTO> simulirajCepModule(@RequestBody CEPSimulacijaZahtevDTO dto) {
        KieSession kieSession = kieContainer.newKieSession("fittrack-session");

        Korisnik korisnik = new Korisnik();
        korisnik.setId(dto.korisnikId);
        korisnik.setGodine(dto.godine);
        korisnik.setPulsUMirovanju(dto.pulsUMirovanju);
        korisnik.setPritisakKategorija(dto.pritisakKategorija);
        korisnik.setMaxPuls(0);

        TreningIntenzitet intenzitet = new TreningIntenzitet(dto.korisnikId, dto.intenzitetTreninga);
        AktivnaSesija aktivnaSesija = new AktivnaSesija(dto.korisnikId, dto.aktivnaSesija);

        kieSession.insert(korisnik);
        kieSession.insert(intenzitet);
        kieSession.insert(aktivnaSesija);

        kieSession.fireAllRules();

        long pocetnoVremeMilis = System.currentTimeMillis();
        long redniBroj = 1;

        if (dto.pulsniDogadjaji != null) {
            for (PulsSimulacijaEventDTO pEventDto : dto.pulsniDogadjaji) {
                long simuliraniMilis = pocetnoVremeMilis + ((long) pEventDto.sekundiOdPocetka * 1000);
                Date simuliraniTimestamp = new Date(simuliraniMilis);

                PulsEvent pulsEvent = new PulsEvent(
                        dto.korisnikId,
                        pEventDto.bpm,
                        simuliraniTimestamp,
                        redniBroj++);

                kieSession.insert(pulsEvent);
                kieSession.fireAllRules();
            }
        }

        List<CEPAlarmDTO> izlazniAlarmi = new ArrayList<>();
        for (Object obj : kieSession.getObjects()) {
            if (obj instanceof CEPAlarm) {
                CEPAlarm alarm = (CEPAlarm) obj;
                izlazniAlarmi.add(new CEPAlarmDTO(alarm.getNazivPravila(), alarm.getNivio(), alarm.getPoruka()));
            }
        }

        kieSession.dispose();

        CEPSimulacijaIzlazDTO izlaz = new CEPSimulacijaIzlazDTO(
                korisnik.getMaxPuls(),
                korisnik.getCiljnaZonaDonja(),
                korisnik.getCiljnaZonaGornja(),
                izlazniAlarmi);

        return ResponseEntity.ok(izlaz);
    }
}