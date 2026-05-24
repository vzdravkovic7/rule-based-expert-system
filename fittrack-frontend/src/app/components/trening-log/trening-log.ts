import { Component, ChangeDetectorRef } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { LogStavkaDTO, UnosTreningaDTO, TreningPlanDTO } from '../../models/fittrack.dto';

@Component({
  selector: 'app-trening-log',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './trening-log.html',
  styleUrl: './trening-log.css'
})
export class TreningLogComponent {
  trenutniRpe = 7;
  trenutniDatum = new Date().toISOString().substring(0, 10);

  privremeniLogovi: LogStavkaDTO[] = [
    { rpe: 6, datum: '2026-05-10' },
    { rpe: 7, datum: '2026-05-12' },
    { rpe: 5, datum: '2026-05-14' }
  ];

  osvezenPlan: TreningPlanDTO | null = null;
  ucitavanje = false;

  constructor(private cdr: ChangeDetectorRef) { }

  dodajLogUListu() {
    if (this.trenutniRpe >= 1 && this.trenutniRpe <= 10) {
      this.privremeniLogovi.push({
        rpe: this.trenutniRpe,
        datum: this.trenutniDatum
      });
    }
  }

  obrisiLog(index: number) {
    this.privremeniLogovi.splice(index, 1);
  }

  async posaljiUProgresiju() {
    this.ucitavanje = true;
    try {
      const slanjePodataka: UnosTreningaDTO = {
        korisnikId: 1,
        logovi: this.privremeniLogovi
      };

      const res = await fetch('http://localhost:8080/api/trening/progresija', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(slanjePodataka)
      });

      this.osvezenPlan = await res.json();
    } catch (e) {
      console.error("Greška pri proveri progresa:", e);
    } finally {
      this.ucitavanje = false;
      this.cdr.detectChanges();
    }
  }
}