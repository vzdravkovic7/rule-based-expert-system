import { Component, ChangeDetectorRef } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { BackwardZahtevDTO, BackwardOdgovorDTO } from '../../models/fittrack.dto';

@Component({
  selector: 'app-backward-demonstracija',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './backward-demonstracija.html',
  styleUrl: './backward-demonstracija.css'
})
export class BackwardDemonstracijaComponent {
  godine = 24;
  bmi = 23.5;
  pritisakKategorija: 'NORMALAN' | 'HIPERTENZIJA_1' | 'HIPERTENZIJA_2' = 'NORMALAN';
  tipCilja: 'GUBITAK_MASTI' | 'MISICNA_MASA' = 'GUBITAK_MASTI';
  ciljniProcenatMasti = 12.0;
  kalorijskiDeficit = 550;
  tipTreninga: 'KARDIO' | 'SNAGA' | 'KOMBINOVANO' = 'KARDIO';
  nivoAktivnosti: 'NIZAK' | 'SREDNJI' | 'VISOK' = 'VISOK';
  dnevniProtein = 150;
  promenaMasePoslednjihNedelja = -0.5;

  rezultat: BackwardOdgovorDTO | null = null;
  ucitavanje = false;

  constructor(private cdr: ChangeDetectorRef) { }

  async pokreniBackwardRezonovanje() {
    this.ucitavanje = true;
    try {
      const zahtevData: BackwardZahtevDTO = {
        godine: this.godine,
        bmi: this.bmi,
        pritisakKategorija: this.pritisakKategorija,
        tipCilja: this.tipCilja,
        ciljniProcenatMasti: this.ciljniProcenatMasti,
        kalorijskiDeficit: this.kalorijskiDeficit,
        tipTreninga: this.tipTreninga,
        nivoAktivnosti: this.nivoAktivnosti,
        dnevniProtein: this.dnevniProtein,
        promenaMasePoslednjihNedelja: this.promenaMasePoslednjihNedelja
      };

      const res = await fetch('http://localhost:8080/api/backward/testiraj-ciljeve', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(zahtevData)
      });

      this.rezultat = await res.json();
    } catch (e) {
      console.error("Greška pri izvršavanju backward chaining-a:", e);
    } finally {
      this.ucitavanje = false;
      this.cdr.detectChanges();
    }
  }

  postaviScenario(tip: string) {
    if (tip === 'idealan') {
      this.godine = 24; this.bmi = 23.5; this.pritisakKategorija = 'NORMALAN';
      this.tipCilja = 'GUBITAK_MASTI'; this.ciljniProcenatMasti = 12.0;
      this.kalorijskiDeficit = 550; this.tipTreninga = 'KARDIO';
      this.nivoAktivnosti = 'VISOK'; this.dnevniProtein = 150;
      this.promenaMasePoslednjihNedelja = -0.5;
    } else if (tip === 'rizik') {
      this.godine = 52; this.bmi = 31.2; this.pritisakKategorija = 'HIPERTENZIJA_1';
      this.tipCilja = 'GUBITAK_MASTI'; this.ciljniProcenatMasti = 14.0;
      this.kalorijskiDeficit = 600; this.tipTreninga = 'KARDIO';
      this.nivoAktivnosti = 'VISOK'; this.dnevniProtein = 145;
      this.promenaMasePoslednjihNedelja = -0.4;
    } else if (tip === 'stagnacija') {
      this.godine = 24; this.bmi = 25.0; this.pritisakKategorija = 'NORMALAN';
      this.tipCilja = 'GUBITAK_MASTI'; this.ciljniProcenatMasti = 12.0;
      this.kalorijskiDeficit = 300; this.tipTreninga = 'SNAGA';
      this.nivoAktivnosti = 'NIZAK'; this.dnevniProtein = 110;
      this.promenaMasePoslednjihNedelja = -0.1;
    }
  }
}