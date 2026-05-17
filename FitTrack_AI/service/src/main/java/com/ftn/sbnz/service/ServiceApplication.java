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
import java.util.Collection;

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
				new Korisnik(3L, "Jovana", 31, 168.0, 55.0, 145, 95, 800.0, "GUBITAK_MASTI")
		};

		for (Korisnik k : testKorisnici) {
			KieSession kSession = kc.newKieSession("fittrack-session");

			kSession.insert(k);
			kSession.insert(new Vezba("Brzi hod na traci", "KARDIO", "NIZAK", false));
			kSession.insert(new Vezba("Stacionarni bicikl", "KARDIO", "NIZAK", false));

			if (k.getIme().equals("Marko")) {
				kSession.insert(new TreningLog(1L, 6, LocalDate.now().minusDays(5)));
				kSession.insert(new TreningLog(1L, 5, LocalDate.now().minusDays(3)));
				kSession.insert(new TreningLog(1L, 7, LocalDate.now().minusDays(1)));
			}

			System.out.println("\n>>> [Drools] Pokretanje sesije za korisnika: " + k.getIme());
			kSession.fireAllRules();

			System.out.println("[Drools] Izvrsavanje zavrseno.\n");

			TreningPlan nadjeniPlan = null;
			NutritivniPlan nadjeniNutri = null;

			Collection<Object> objektiUSesiji = (Collection<Object>) kSession.getObjects();
			for (Object obj : objektiUSesiji) {
				if (obj instanceof TreningPlan) {
					nadjeniPlan = (TreningPlan) obj;
				} else if (obj instanceof NutritivniPlan) {
					nadjeniNutri = (NutritivniPlan) obj;
				}
			}

			System.out.println("=======================================================");
			System.out.println("   FINALNI REZULTAT REZONOVANJA ZA KORISNIKA: " + k.getIme().toUpperCase());
			System.out.println("=======================================================");
			System.out.println("1. ANALIZA PROFILA:");
			System.out.println("   - BMI Kategorija: " + k.getBmiKategorija());
			System.out.println("   - Stanje pritiska: " + k.getPritisakKategorija());
			System.out.println("   - Nivo aktivnosti: " + k.getNivoAktivnosti());
			System.out.println("-------------------------------------------------------");

			if (nadjeniPlan != null) {
				System.out.println("2. GENERISANI TRENING PLAN:");
				System.out.println("   - Raspored: " + nadjeniPlan.getKardioDani() + "x Kardio | "
						+ nadjeniPlan.getSnagaDani() + "x Snaga nedeljno");
				System.out.println("   - Parametri snage: " + nadjeniPlan.getSerijeSnaga() + " serije x "
						+ nadjeniPlan.getPonavljanjaSnaga() + " ponavljanja");
				System.out.println("   - Kardio minutaza: " + nadjeniPlan.getKardioMinuta() + " minuta po sesiji");
				System.out.println("   - Selektovane bezbedne vežbe: " + nadjeniPlan.getSelektovaneVezbe());
			}
			System.out.println("-------------------------------------------------------");

			if (nadjeniNutri != null) {
				String tipBilansa = k.getCilj().equals("GUBITAK_MASTI") ? "SA DEFICITOM" : "SA SUFICITOM";
				System.out.println("3. NUTRITIVNE PREPORUKE (Harris-Benedict):");
				System.out.println(
						"   - BMR (Bazalni metabolizam): " + String.format("%.1f", nadjeniNutri.getBmr()) + " kcal");
				System.out.println(
						"   - TDEE (Ukupna potrosnja): " + String.format("%.1f", nadjeniNutri.getTdee()) + " kcal");
				System.out.println("   - Ciljani unos (" + tipBilansa + "): "
						+ String.format("%.0f", nadjeniNutri.getCiljaneKalorije()) + " kcal");
				System.out.println("   - Ciljani makronutrijenti:");
				System.out.println(
						"     * Proteini: " + String.format("%.1f", nadjeniNutri.getProteiniGrama())
								+ "g (ocuvanje mase)");
				System.out.println("     * Masti: " + String.format("%.1f", nadjeniNutri.getMastiGrama()) + "g");
				System.out.println(
						"     * Ugljeni hidrati: " + String.format("%.1f", nadjeniNutri.getUgljeniHidratiGrama())
								+ "g");
			}
			System.out.println("=======================================================\n");

			kSession.dispose();
		}
	}
}