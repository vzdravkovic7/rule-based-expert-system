package com.ftn.sbnz.service;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ftn.sbnz.model.Korisnik;
import com.ftn.sbnz.model.TreningPlan;
import com.ftn.sbnz.model.NutritivniPlan;
import com.ftn.sbnz.model.TreningLog;
import com.ftn.sbnz.model.Vezba;

import java.time.LocalDate;

@SpringBootApplication
public class ServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

	@Bean
	public KieContainer kieContainer() {
		KieServices ks = KieServices.Factory.get();
		return ks.newKieContainer(ks.newReleaseId("com.ftn.sbnz", "kjar", "0.0.1-SNAPSHOT"));
	}

	@Override
	public void run(String... args) throws Exception {
		KieContainer kc = kieContainer();

		Korisnik[] testKorisnici = {
				new Korisnik(1L, "Marko", 28, 180.0, 95.0, 138, 88, 320.0, "GUBITAK_MASTI"),
				new Korisnik(2L, "Nikola", 24, 182.0, 80.0, 115, 75, 2000.0, "HIPERTROFIJA"),
				new Korisnik(3L, "Jovana", 31, 168.0, 55.0, 145, 95, 800.0, "GUBITAK_MASTI"),
				new Korisnik(4L, "Petar", 45, 175.0, 80.0, 118, 78, 1000.0, "OPSTA_KONDICIJA"),
				new Korisnik(5L, "Milan", 66, 170.0, 110.0, 165, 105, 100.0, "GUBITAK_MASTI")
		};

		testKorisnici[0].setDnevniUnosKalorija(2000.0);
		testKorisnici[0].setDnevniUnosProteina(210.0);
		testKorisnici[1].setDnevniUnosKalorija(3800.0);
		testKorisnici[1].setDnevniUnosProteina(170.0);
		testKorisnici[2].setDnevniUnosKalorija(1000.0);
		testKorisnici[2].setDnevniUnosProteina(40.0);

		Vezba[] bazaVezbi = {
				new Vezba("Brzi hod na traci", "KARDIO", "NIZAK", false),
				new Vezba("Stacionarni bicikl", "KARDIO", "NIZAK", false),
				new Vezba("Cucanj sa tegovima", "SNAGA", "VISOK", false),
				new Vezba("Potisak sa klupe", "SNAGA", "VISOK", false),
				new Vezba("Nozna ekstenzija", "SNAGA", "UMEREN", false)
		};

		for (Korisnik k : testKorisnici) {
			KieSession kSession = kc.newKieSession("fittrack-session");
			kSession.insert(k);
			for (Vezba v : bazaVezbi) {
				kSession.insert(v);
			}

			if (k.getIme().equals("Marko")) {
				kSession.insert(new TreningLog(1L, 6, LocalDate.now().minusDays(5)));
				kSession.insert(new TreningLog(1L, 5, LocalDate.now().minusDays(3)));
				kSession.insert(new TreningLog(1L, 7, LocalDate.now().minusDays(1)));
			}
			if (k.getIme().equals("Nikola")) {
				TreningPlan maxPlan = new TreningPlan(2L);
				maxPlan.setSerijeSnaga(3);
				kSession.insert(maxPlan);

				kSession.insert(new TreningLog(2L, 6, LocalDate.now().minusDays(4)));
				kSession.insert(new TreningLog(2L, 5, LocalDate.now().minusDays(2)));
				kSession.insert(new TreningLog(2L, 6, LocalDate.now().minusDays(1)));
			}
			if (k.getIme().equals("Jovana")) {
				kSession.insert(new TreningLog(3L, 6, LocalDate.now().minusDays(25)));
			}
			if (k.getIme().equals("Petar")) {
				kSession.insert(new TreningLog(4L, 9, LocalDate.now().minusDays(4)));
				kSession.insert(new TreningLog(4L, 10, LocalDate.now().minusDays(2)));
				kSession.insert(new TreningLog(4L, 9, LocalDate.now().minusDays(1)));
			}

			System.out.println("\n>>> [Drools] Pokretanje sesije: " + k.getIme());
			kSession.fireAllRules();

			TreningPlan nadjeniPlan = null;
			NutritivniPlan nadjeniNutri = null;

			for (Object obj : kSession.getObjects()) {
				if (obj instanceof TreningPlan)
					nadjeniPlan = (TreningPlan) obj;
				else if (obj instanceof NutritivniPlan)
					nadjeniNutri = (NutritivniPlan) obj;
			}

			System.out.println("=======================================================");
			System.out.println("   REZULTAT REZONOVANJA: " + k.getIme().toUpperCase());
			System.out.println("=======================================================");
			System.out.println("BMI: " + k.getBmiKategorija() + " | Pritisak: " + k.getPritisakKategorija()
					+ " | Aktivnost: " + k.getNivoAktivnosti());

			if (nadjeniPlan != null) {
				System.out.println("Trening plan: " + nadjeniPlan.getKardioDani() + "x Kardio, "
						+ nadjeniPlan.getSnagaDani() + "x Snaga");
				System.out.println("Parametri: " + nadjeniPlan.getSerijeSnaga() + " serije, "
						+ nadjeniPlan.getKardioMinuta() + " min kardio");
				System.out.println("Vezbe: " + nadjeniPlan.getSelektovaneVezbe());
			}
			if (nadjeniNutri != null) {
				System.out.println(
						"Kalorije cilj: " + String.format("%.0f", nadjeniNutri.getCiljaneKalorije()) + " kcal");
				System.out.println("Status unosa hrane: "
						+ (nadjeniNutri.getKomentarUnosa() != null ? nadjeniNutri.getKomentarUnosa() : "OK"));
			}
			System.out.println("=======================================================\n");

			kSession.dispose();
		}
	}
}