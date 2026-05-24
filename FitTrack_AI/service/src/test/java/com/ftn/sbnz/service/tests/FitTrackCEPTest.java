package com.ftn.sbnz.service.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.drools.core.time.SessionPseudoClock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.ftn.sbnz.model.AktivnaSesija;
import com.ftn.sbnz.model.CEPAlarm;
import com.ftn.sbnz.model.Korisnik;
import com.ftn.sbnz.model.PulsEvent;
import com.ftn.sbnz.model.TreningIntenzitet;

public class FitTrackCEPTest {

    private KieSession ksession;
    private SessionPseudoClock clock;
    private final Long KORISNIK_ID = 1L;

    @Before
    public void setUp() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.newKieClasspathContainer();
        this.ksession = kc.newKieSession("fittrack-session");
        this.clock = ksession.getSessionClock();
    }

    @After
    public void tearDown() {
        if (this.ksession != null) {
            this.ksession.dispose();
        }
    }

    @Test
    public void testCEP_TahikardijaAlarm_FiresWhenAverageExceeds85Percent() {
        Korisnik korisnik = new Korisnik();
        korisnik.setId(KORISNIK_ID);
        korisnik.setGodine(20);
        korisnik.setPulsUMirovanju(60);
        korisnik.setMaxPuls(0);

        TreningIntenzitet intenzitet = new TreningIntenzitet(KORISNIK_ID, "VISOK");

        ksession.insert(korisnik);
        ksession.insert(intenzitet);
        ksession.fireAllRules();

        long redniBroj = 1;
        for (int i = 0; i < 5; i++) {
            Date timestamp = new Date(clock.getCurrentTime());
            PulsEvent event = new PulsEvent(KORISNIK_ID, 170, timestamp, redniBroj++);
            ksession.insert(event);

            clock.advanceTime(15, TimeUnit.SECONDS);
            ksession.fireAllRules();
        }

        Collection<CEPAlarm> alarmi = getAlarmiIzSesije();
        long brojTahikardijaAlarma = alarmi.stream()
                .filter(a -> a.getNazivPravila().equals("CEP_TahikardijaAlarm"))
                .count();

        assertThat(brojTahikardijaAlarma, equalTo(1L));
    }

    @Test
    public void testCEP_PulsVanZone_Upozorenje_FiresAfter10ConsecutiveHighBeats() {
        Korisnik korisnik = new Korisnik();
        korisnik.setId(KORISNIK_ID);
        korisnik.setGodine(20);
        korisnik.setPulsUMirovanju(60);
        korisnik.setPritisakKategorija("NORMALAN");
        korisnik.setMaxPuls(0);

        TreningIntenzitet intenzitet = new TreningIntenzitet(KORISNIK_ID, "UMEREN");

        ksession.insert(korisnik);
        ksession.insert(intenzitet);
        ksession.fireAllRules();

        long redniBroj = 1;
        for (int i = 1; i <= 9; i++) {
            Date timestamp = new Date(clock.getCurrentTime());
            PulsEvent event = new PulsEvent(KORISNIK_ID, 150, timestamp, redniBroj++);
            ksession.insert(event);

            clock.advanceTime(10, TimeUnit.SECONDS);
            ksession.fireAllRules();

            Collection<CEPAlarm> alarmi = getAlarmiIzSesije();
            long brojAlarma = alarmi.stream().filter(a -> a.getNazivPravila().equals("CEP_PulsVanZone_Upozorenje"))
                    .count();
            assertThat(brojAlarma, equalTo(0L));
        }

        Date timestamp = new Date(clock.getCurrentTime());
        PulsEvent desetiEvent = new PulsEvent(KORISNIK_ID, 150, timestamp, redniBroj++);
        ksession.insert(desetiEvent);
        ksession.fireAllRules();

        Collection<CEPAlarm> alarmi = getAlarmiIzSesije();
        long brojAlarma = alarmi.stream().filter(a -> a.getNazivPravila().equals("CEP_PulsVanZone_Upozorenje")).count();
        assertThat(brojAlarma, equalTo(1L));
    }

    @Test
    public void testCEP_PulsVanZone_Kriticno_FiresWhenHighFor10MinutesWithRiskyPressure() {
        Korisnik korisnik = new Korisnik();
        korisnik.setId(KORISNIK_ID);
        korisnik.setGodine(30);
        korisnik.setPulsUMirovanju(70);
        korisnik.setPritisakKategorija("HIPERTENZIJA_1");
        korisnik.setMaxPuls(0);

        TreningIntenzitet intenzitet = new TreningIntenzitet(KORISNIK_ID, "NIZAK");

        ksession.insert(korisnik);
        ksession.insert(intenzitet);
        ksession.fireAllRules();

        long redniBroj = 1;
        for (int i = 0; i < 11; i++) {
            Date timestamp = new Date(clock.getCurrentTime());
            PulsEvent event = new PulsEvent(KORISNIK_ID, 160, timestamp, redniBroj++);
            ksession.insert(event);

            clock.advanceTime(1, TimeUnit.MINUTES);
            ksession.fireAllRules();
        }

        Collection<CEPAlarm> alarmi = getAlarmiIzSesije();
        long brojKriticnihAlarma = alarmi.stream()
                .filter(a -> a.getNazivPravila().equals("CEP_PulsVanZone_Kritično"))
                .count();

        assertThat(brojKriticnihAlarma, equalTo(1L));
    }

    @Test
    public void testCEP_BradikardijaTokomVezbe_FiresWhenAverageDropsBelow50InActiveSession() {
        Korisnik korisnik = new Korisnik();
        korisnik.setId(KORISNIK_ID);
        korisnik.setGodine(25);
        korisnik.setPulsUMirovanju(65);

        AktivnaSesija aktivnaSesija = new AktivnaSesija(KORISNIK_ID, true);

        ksession.insert(korisnik);
        ksession.insert(aktivnaSesija);
        ksession.fireAllRules();

        long redniBroj = 1;
        for (int i = 0; i < 4; i++) {
            Date timestamp = new Date(clock.getCurrentTime());
            PulsEvent event = new PulsEvent(KORISNIK_ID, 45, timestamp, redniBroj++);
            ksession.insert(event);

            clock.advanceTime(1, TimeUnit.MINUTES);
            ksession.fireAllRules();
        }

        Collection<CEPAlarm> alarmi = getAlarmiIzSesije();
        long bradikardijaAlarmi = alarmi.stream()
                .filter(a -> a.getNazivPravila().equals("CEP_BradikardijaTokomVezbe"))
                .count();

        assertThat(bradikardijaAlarmi, equalTo(1L));
    }

    @Test
    public void testCEP_OptimalnaZonaPotvrda_FiresAfter10ConsecutiveOptimalBeats() {
        Korisnik korisnik = new Korisnik();
        korisnik.setId(KORISNIK_ID);
        korisnik.setGodine(20);
        korisnik.setPulsUMirovanju(60);
        korisnik.setMaxPuls(0);

        TreningIntenzitet intenzitet = new TreningIntenzitet(KORISNIK_ID, "UMEREN");

        ksession.insert(korisnik);
        ksession.insert(intenzitet);
        ksession.fireAllRules();

        long redniBroj = 1;
        for (int i = 1; i <= 10; i++) {
            Date timestamp = new Date(clock.getCurrentTime());
            PulsEvent event = new PulsEvent(KORISNIK_ID, 125, timestamp, redniBroj++);
            ksession.insert(event);

            clock.advanceTime(30, TimeUnit.SECONDS);
            ksession.fireAllRules();
        }

        Collection<CEPAlarm> alarmi = getAlarmiIzSesije();
        long potvrdaAlarmi = alarmi.stream()
                .filter(a -> a.getNazivPravila().equals("CEP_OptimalnaZonaPotvrda"))
                .count();

        assertThat(potvrdaAlarmi, equalTo(1L));
    }

    @SuppressWarnings("unchecked")
    private Collection<CEPAlarm> getAlarmiIzSesije() {
        return (Collection<CEPAlarm>) ksession.getObjects(new ClassObjectFilter(CEPAlarm.class));
    }
}