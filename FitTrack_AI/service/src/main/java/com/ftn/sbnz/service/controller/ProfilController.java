package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.model.Korisnik;
import com.ftn.sbnz.model.TreningIntenzitet;
import com.ftn.sbnz.model.TreningPlan;
import com.ftn.sbnz.model.Vezba;
import com.ftn.sbnz.service.dto.KorisnikProfilDTO;
import com.ftn.sbnz.service.dto.TreningPlanDTO;
import com.ftn.sbnz.service.repository.VezbaRepository;
import org.drools.template.DataProviderCompiler;
import org.drools.template.objects.ArrayDataProvider;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

        String generisaniTemplateDrl = "";
        try {
            InputStream templateStream = getClass().getResourceAsStream("/rules/trening_blokovi.drt");

            java.io.File csvFile = new java.io.File("external/trening_blokovi.template.csv");
            InputStream csvStream;

            if (csvFile.exists()) {
                System.out.println(
                        ">>> [Drools] Učitavam tabelu sa eksterne admin lokacije: " + csvFile.getAbsolutePath());
                csvStream = new java.io.FileInputStream(csvFile);
            } else {
                System.out.println(
                        ">>> [Drools] Eksterni fajl nije pronađen na putanji 'external/', koristim podrazumevani iz resursa.");
                csvStream = getClass().getResourceAsStream("/templates/trening_blokovi.template.csv");
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(csvStream));
            List<String[]> csvRows = new ArrayList<>();
            String line;

            boolean isHeader = true;
            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                if (!line.trim().isEmpty()) {
                    csvRows.add(line.split(","));
                }
            }
            reader.close();

            ArrayDataProvider dataProvider = new ArrayDataProvider(csvRows.toArray(new String[0][0]));
            DataProviderCompiler compiler = new DataProviderCompiler();
            generisaniTemplateDrl = compiler.compile(dataProvider, templateStream);

            System.out.println(">>> [Drools Template] Šablon uspešno kompajliran.");
        } catch (Exception e) {
            System.err.println("Greška pri kompajliranju template-a: " + e.getMessage());
            e.printStackTrace();
        }

        KieSession kieSession = kieContainer.newKieSession("fittrack-session");

        Korisnik korisnik = new Korisnik(dto.id, dto.ime, dto.godine, dto.visina, dto.telesnaMasa,
                dto.sistolickiPritisak, dto.dijastolickiPritisak, dto.metMinutaNedeljno, dto.cilj);

        List<Vezba> bazaVezbi = vezbaRepository.findAll();

        kieSession.insert(korisnik);
        for (Vezba v : bazaVezbi) {
            kieSession.insert(v);
        }

        System.out.println(">>> [API] Pokretanje primarnih kjar pravila (Modul 1, 2, 3...)");
        kieSession.fireAllRules();

        Korisnik klasifikovaniKorisnik = korisnik;
        TreningIntenzitet nadjeniIntenzitet = null;
        TreningPlan generisaniPlan = null;

        for (Object obj : kieSession.getObjects()) {
            if (obj instanceof TreningIntenzitet) {
                nadjeniIntenzitet = (TreningIntenzitet) obj;
            }
            if (obj instanceof TreningPlan) {
                generisaniPlan = (TreningPlan) obj;
            }
        }

        if (generisaniPlan != null && nadjeniIntenzitet != null && !generisaniTemplateDrl.isEmpty()) {
            System.out.println(">>> [Drools] Pokretanje dinamičkih pravila iz šablona za intenzitet: "
                    + nadjeniIntenzitet.getIntenzitet());

            KieHelper kieHelper = new KieHelper();
            kieHelper.addContent(generisaniTemplateDrl, ResourceType.DRL);
            KieSession templateSession = kieHelper.build().newKieSession();

            templateSession.insert(nadjeniIntenzitet);
            templateSession.insert(generisaniPlan);
            templateSession.insert(klasifikovaniKorisnik);

            templateSession.fireAllRules();
            templateSession.dispose();
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
        izlazniDto.porukaProgresa = generisaniPlan.getPorukaProgresa();

        return ResponseEntity.ok(izlazniDto);
    }
}