import { Component, ChangeDetectorRef } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CEPSimulacijaZahtevDTO, PulsSimulacijaEventDTO, CEPSimulacijaIzlazDTO } from '../../models/fittrack.dto';

@Component({
  selector: 'app-cep-simulacija',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './cep-simulacija.html',
  styleUrl: './cep-simulacija.css'
})
export class CepSimulacijaComponent {
  godine = 25;
  pulsUMirovanju = 65;
  pritisakKategorija = 'NORMALAN';
  intenzitetTreninga: 'NIZAK' | 'UMEREN' | 'VISOK' = 'UMEREN';
  aktivnaSesija = true;

  trenutniBpm = 130;
  trenutneSekunde = 10;

  privremeniDogadjaji: PulsSimulacijaEventDTO[] = [];
  rezultat: CEPSimulacijaIzlazDTO | null = null;
  ucitavanje = false;

  constructor(private cdr: ChangeDetectorRef) { }

  dodajMerenje() {
    this.privremeniDogadjaji.push({
      bpm: this.trenutniBpm,
      sekundiOdPocetka: this.trenutneSekunde
    });
    this.privremeniDogadjaji.sort((a, b) => a.sekundiOdPocetka - b.sekundiOdPocetka);
  }

  ukloniMerenje(index: number) {
    this.privremeniDogadjaji.splice(index, 1);
  }

  ucitajScenario(tip: 'TAHIKARDIJA' | 'VAN_ZONE' | 'KRITICNO' | 'BRADIKARDIJA' | 'OPTIMALNO') {
    this.privremeniDogadjaji = [];

    if (tip === 'TAHIKARDIJA') {
      this.godine = 20; this.intenzitetTreninga = 'VISOK'; this.pritisakKategorija = 'NORMALAN';
      this.privremeniDogadjaji = [
        { bpm: 170, sekundiOdPocetka: 10 }, { bpm: 175, sekundiOdPocetka: 40 },
        { bpm: 172, sekundiOdPocetka: 80 }, { bpm: 178, sekundiOdPocetka: 120 }
      ];
    } else if (tip === 'VAN_ZONE') {
      this.godine = 30; this.intenzitetTreninga = 'UMEREN'; this.pritisakKategorija = 'NORMALAN';
      for (let i = 1; i <= 10; i++) {
        this.privremeniDogadjaji.push({ bpm: 145, sekundiOdPocetka: i * 20 });
      }
    } else if (tip === 'KRITICNO') {
      this.godine = 40; this.intenzitetTreninga = 'NIZAK'; this.pritisakKategorija = 'HIPERTENZIJA_1';
      this.privremeniDogadjaji = [
        { bpm: 145, sekundiOdPocetka: 10 }, { bpm: 148, sekundiOdPocetka: 200 },
        { bpm: 146, sekundiOdPocetka: 400 }, { bpm: 150, sekundiOdPocetka: 590 }
      ];
    } else if (tip === 'BRADIKARDIJA') {
      this.godine = 25; this.intenzitetTreninga = 'UMEREN'; this.aktivnaSesija = true;
      this.privremeniDogadjaji = [
        { bpm: 45, sekundiOdPocetka: 10 }, { bpm: 48, sekundiOdPocetka: 60 },
        { bpm: 46, sekundiOdPocetka: 120 }, { bpm: 47, sekundiOdPocetka: 170 }
      ];
    } else if (tip === 'OPTIMALNO') {
      this.godine = 20; this.intenzitetTreninga = 'UMEREN'; this.pritisakKategorija = 'NORMALAN';
      for (let i = 1; i <= 10; i++) {
        this.privremeniDogadjaji.push({ bpm: 125, sekundiOdPocetka: i * 30 });
      }
    }
    this.cdr.detectChanges();
  }

  async posaljiUStreamObradu() {
    this.ucitavanje = true;
    try {
      const body: CEPSimulacijaZahtevDTO = {
        korisnikId: 999,
        godine: this.godine,
        pulsUMirovanju: this.pulsUMirovanju,
        pritisakKategorija: this.pritisakKategorija,
        intenzitetTreninga: this.intenzitetTreninga,
        aktivnaSesija: this.aktivnaSesija,
        pulsniDogadjaji: this.privremeniDogadjaji
      };

      const res = await fetch('http://localhost:8080/api/cep/simuliraj', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(body)
      });

      this.rezultat = await res.json();
    } catch (e) {
      console.error("Greška tokom CEP simulacije:", e);
    } finally {
      this.ucitavanje = false;
      this.cdr.detectChanges();
    }
  }
}