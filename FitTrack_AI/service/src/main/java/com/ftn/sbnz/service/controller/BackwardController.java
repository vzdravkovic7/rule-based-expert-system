package com.ftn.sbnz.service.controller;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;

import com.ftn.sbnz.model.Korisnik;
import com.ftn.sbnz.model.CiljKorisnika;
import com.ftn.sbnz.model.TrenutniPlan;
import com.ftn.sbnz.model.RezultatProvereCilja;
import com.ftn.sbnz.service.dto.BackwardZahtevDTO;
import com.ftn.sbnz.service.dto.BackwardOdgovorDTO;

@RestController
@RequestMapping("/api/backward")
@CrossOrigin(origins = "http://localhost:4200")
public class BackwardController {

    @Autowired
    private KieContainer kieContainer;

    @PostMapping("/testiraj-ciljeve")
    public ResponseEntity<BackwardOdgovorDTO> testirajCiljeveUnazad(@RequestBody BackwardZahtevDTO dto) {
        KieSession ksession = kieContainer.newKieSession("fittrack-session");
        Long lažniKorisnikId = 999L;

        Korisnik korisnik = new Korisnik();
        korisnik.setId(lažniKorisnikId);
        korisnik.setGodine(dto.getGodine());
        korisnik.setVisina(100.0);
        korisnik.setTelesnaMasa(dto.getBmi());
        korisnik.setPritisakKategorija(dto.getPritisakKategorija());

        CiljKorisnika cilj = new CiljKorisnika(lažniKorisnikId, dto.getTipCilja(), dto.getCiljniProcenatMasti());

        TrenutniPlan plan = new TrenutniPlan();
        plan.setKorisnikId(lažniKorisnikId);
        plan.setKalorijskiDeficit(dto.getKalorijskiDeficit());
        plan.setTipTreninga(dto.getTipTreninga());
        plan.setNivoAktivnosti(dto.getNivoAktivnosti());
        plan.setDnevniProtein(dto.getDnevniProtein());
        plan.setPromenaMasePoslednjihNedelja(dto.getPromenaMasePoslednjihNedelja());

        RezultatProvereCilja rezultat = new RezultatProvereCilja(lažniKorisnikId);

        ksession.insert(korisnik);
        ksession.insert(cilj);
        ksession.insert(plan);
        ksession.insert(rezultat);

        ksession.fireAllRules();

        boolean telesniSastavZadovoljen = ksession
                .getQueryResults("Query_DostiziCilj_TelesniSastav", lažniKorisnikId, rezultat).size() > 0;
        boolean nutritivniZadovoljen = ksession.getQueryResults("Query_NutritivePreduslovi", lažniKorisnikId, rezultat)
                .size() > 0;

        boolean imaZdravstvenihRizika = ksession.getQueryResults("Query_BezbednostPrograma", lažniKorisnikId, rezultat)
                .size() > 0;
        boolean programBezbedan = !imaZdravstvenihRizika;

        boolean detektovanPresporTempo = ksession
                .getQueryResults("Query_VremeDoIspunjenjacilja", lažniKorisnikId, rezultat).size() > 0;
        boolean vremeDoCiljaDovoljno = !detektovanPresporTempo;

        ksession.dispose();

        java.util.List<String> cisteKontraindikacije = rezultat.getKontraindikacije().stream().distinct()
                .collect(Collectors.toList());

        BackwardOdgovorDTO odgovor = new BackwardOdgovorDTO(
                telesniSastavZadovoljen,
                nutritivniZadovoljen,
                programBezbedan,
                vremeDoCiljaDovoljno,
                detektovanPresporTempo ? 24 : 8,
                cisteKontraindikacije);

        return ResponseEntity.ok(odgovor);
    }
}