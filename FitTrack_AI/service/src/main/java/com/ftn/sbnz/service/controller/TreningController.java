package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.model.Korisnik;
import com.ftn.sbnz.model.TreningLog;
import com.ftn.sbnz.model.TreningPlan;
import com.ftn.sbnz.service.dto.TreningPlanDTO;
import com.ftn.sbnz.service.dto.UnosTreningaDTO;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trening")
@CrossOrigin(origins = "http://localhost:4200")
public class TreningController {

    @Autowired
    private KieContainer kieContainer;

    @PostMapping("/progresija")
    public ResponseEntity<TreningPlanDTO> proveriProgresiju(@RequestBody UnosTreningaDTO dto) {
        KieSession kieSession = kieContainer.newKieSession("fittrack-session");

        Korisnik korisnik = new Korisnik();
        korisnik.setId(dto.korisnikId);
        korisnik.setNivoAktivnosti("MINIMALNO_AKTIVAN");

        TreningPlan plan = new TreningPlan(dto.korisnikId);
        plan.setSerijeSnaga(2);
        plan.setKardioMinuta(20);

        kieSession.insert(korisnik);
        kieSession.insert(plan);

        for (UnosTreningaDTO.LogStavkaDTO logDto : dto.logovi) {
            TreningLog log = new TreningLog(dto.korisnikId, logDto.rpe, logDto.datum);
            kieSession.insert(log);
        }

        kieSession.fireAllRules();
        kieSession.dispose();

        TreningPlanDTO osvezenPlan = new TreningPlanDTO();
        osvezenPlan.kardioDani = plan.getKardioDani();
        osvezenPlan.snagaDani = plan.getSnagaDani();
        osvezenPlan.serijeSnaga = plan.getSerijeSnaga();
        osvezenPlan.ponavljanjaSnaga = plan.getPonavljanjaSnaga();
        osvezenPlan.odmorSekundi = plan.getOdmorSekundi();
        osvezenPlan.kardioMinuta = plan.getKardioMinuta();
        osvezenPlan.selektovaneVezbe = plan.getSelektovaneVezbe();
        osvezenPlan.nivoAktivnosti = korisnik.getNivoAktivnosti();
        osvezenPlan.porukaProgresa = plan.getPorukaProgresa();

        return ResponseEntity.ok(osvezenPlan);
    }
}