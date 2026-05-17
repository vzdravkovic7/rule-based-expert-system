package com.ftn.sbnz.service;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ftn.sbnz.model.Korisnik;

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
		System.out.println("\n--- FitTrack AI - Pokretanje baze znanja (Simulacija) ---");

		KieContainer kc = kieContainer();
		KieSession kSession = kc.newKieSession("fittrack-session");

		Korisnik marko = new Korisnik(1L, "Marko", 28, 180.0, 95.0, 138, 88, 320.0, "GUBITAK_MASTI");

		System.out.println("Ubacujemo korisnika " + marko.getIme() + " u radnu memoriju...");
		kSession.insert(marko);

		System.out.println("Pokrecemo Drools engine...");
		int ispaljenaPravila = kSession.fireAllRules();
		System.out.println("Ukupno ispaljeno pravila: " + ispaljenaPravila);

		System.out.println("\n--- Rezultati klasifikacije za korisnika ---");
		System.out.println("BMI Kategorija: " + marko.getBmiKategorija());
		System.out.println("Nivo Aktivnosti: " + marko.getNivoAktivnosti());
		System.out.println("-------------------------------------------\n");

		kSession.dispose();
	}
}