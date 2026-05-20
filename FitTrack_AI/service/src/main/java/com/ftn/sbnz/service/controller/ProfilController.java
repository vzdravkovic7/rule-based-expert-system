package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.model.Korisnik;
import com.ftn.sbnz.model.TreningIntenzitet;
import com.ftn.sbnz.model.TreningPlan;
import com.ftn.sbnz.model.Vezba;
import com.ftn.sbnz.service.dto.KorisnikProfilDTO;
import com.ftn.sbnz.service.dto.TreningPlanDTO;
import com.ftn.sbnz.service.repository.VezbaRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profil")
@CrossOrigin(origins = "http://localhost:4200")
public class ProfilController {

    @Autowired
    private KieContainer kieContainer;

    @Autowired
    private VezbaRepository vezbaRepository;

    @PostMapping("/generisi-plan")
    public ResponseEntity<TreningPlanDTO> generisiInicijalniPlan(@RequestBody KorisnikProfilDTO dto) {
        KieSession kieSession = kieContainer.newKieSession("fittrack-session");

        Korisnik korisnik = new Korisnik(dto.id, dto.ime, dto.godine, dto.visina, dto.telesnaMasa,
                dto.sistolickiPritisak, dto.dijastolickiPritisak, dto.metMinutaNedeljno, dto.cilj);

        List<Vezba> bazaVezbi = vezbaRepository.findAll();

        kieSession.insert(korisnik);
        for (Vezba v : bazaVezbi) {
            kieSession.insert(v);
        }

        System.out.println(">>> [API] Pokretanje Drools sesije prema specifikaciji...");
        kieSession.fireAllRules();

        Korisnik klasifikovaniKorisnik = korisnik;
        TreningIntenzitet nadjeniIntenzitet = null;

        for (Object obj : kieSession.getObjects()) {
            if (obj instanceof TreningIntenzitet) {
                nadjeniIntenzitet = (TreningIntenzitet) obj;
            }
        }

        TreningPlan generisaniPlan = null;
        for (Object obj : kieSession.getObjects()) {
            if (obj instanceof TreningPlan) {
                generisaniPlan = (TreningPlan) obj;
                break;
            }
        }

        kieSession.dispose();

        if (generisaniPlan == null) {
            generisaniPlan = new TreningPlan(korisnik.getId());
        }

        TreningPlanDTO izlazniDto = new TreningPlanDTO(
                generisaniPlan.getKardioDani(), generisaniPlan.getSnagaDani(), generisaniPlan.getSerijeSnaga(),
                generisaniPlan.getPonavljanjaSnaga(), generisaniPlan.getOdmorSekundi(),
                generisaniPlan.getKardioMinuta(),
                generisaniPlan.getSelektovaneVezbe(),
                klasifikovaniKorisnik.getBmiKategorija(),
                klasifikovaniKorisnik.getPritisakKategorija(),
                klasifikovaniKorisnik.getNivoAktivnosti(),
                (nadjeniIntenzitet != null) ? nadjeniIntenzitet.getIntenzitet() : "NIJE ODREĐEN");

        izlazniDto.isSenior = klasifikovaniKorisnik.isSenior();

        return ResponseEntity.ok(izlazniDto);
    }
}