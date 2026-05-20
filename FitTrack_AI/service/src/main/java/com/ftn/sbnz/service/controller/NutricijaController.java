package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.model.Korisnik;
import com.ftn.sbnz.model.NutritivniPlan;
import com.ftn.sbnz.service.dto.NutricijaEvaluacijaDTO;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nutricija")
@CrossOrigin(origins = "http://localhost:4200")
public class NutricijaController {

    @Autowired
    private KieContainer kieContainer;

    @PostMapping("/evaluacija")
    public ResponseEntity<NutricijaEvaluacijaDTO> evaluirajNutriciju(@RequestBody NutricijaEvaluacijaDTO dto) {
        KieSession kieSession = kieContainer.newKieSession("fittrack-session");

        Korisnik korisnik = new Korisnik();
        korisnik.setId(dto.korisnikId);
        korisnik.setIme(dto.pol.equals("ZENSKI") ? "Jovana" : "Marko");
        korisnik.setPol(dto.pol);
        korisnik.setGodine(28);
        korisnik.setVisina(180);
        korisnik.setTelesnaMasa(95.0);
        korisnik.setCilj(dto.cilj);
        korisnik.setNivoAktivnosti("NEAKTIVAN");

        korisnik.setDnevniUnosKalorija(dto.uneteKalorije);
        korisnik.setDnevniUnosProteina(dto.unetiProteini);

        kieSession.insert(korisnik);
        kieSession.fireAllRules();

        NutritivniPlan plan = null;
        for (Object obj : kieSession.getObjects()) {
            if (obj instanceof NutritivniPlan) {
                NutritivniPlan np = (NutritivniPlan) obj;
                if (np.getKorisnikId().equals(dto.korisnikId)) {
                    plan = np;
                }
            }
        }
        kieSession.dispose();

        if (plan != null) {
            dto.ciljaneKalorije = plan.getCiljaneKalorije();
            dto.proteiniGrama = plan.getProteiniGrama();
            dto.mastiGrama = plan.getMastiGrama();
            dto.ugljeniHidratiGrama = plan.getUgljeniHidratiGrama();
            dto.komentarUnosa = plan.getKomentarUnosa();
        }

        return ResponseEntity.ok(dto);
    }
}