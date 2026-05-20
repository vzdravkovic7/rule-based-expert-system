import { Component, ChangeDetectorRef } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { NutricijaEvaluacijaDTO } from '../../models/fittrack.dto';

@Component({
  selector: 'app-nutricija',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './nutricija.html',
  styleUrl: './nutricija.css'
})
export class NutricijaComponent {
  selekcijaPola: 'MUSKI' | 'ZENSKI' = 'MUSKI';
  selekcijaCilja: 'GUBITAK_MASTI' | 'HIPERTROFIJA' = 'GUBITAK_MASTI';
  uneteKalorije = 1800;
  unetiProteini = 110;

  evaluacija: NutricijaEvaluacijaDTO | null = null;
  ucitavanje = false;

  constructor(private cdr: ChangeDetectorRef) { }

  async izracunajNutriciju() {
    this.ucitavanje = true;
    try {
      const slanjePodataka: NutricijaEvaluacijaDTO = {
        korisnikId: 1,
        pol: this.selekcijaPola,
        cilj: this.selekcijaCilja,
        uneteKalorije: this.uneteKalorije,
        unetiProteini: this.unetiProteini
      };

      const res = await fetch('http://localhost:8080/api/nutricija/evaluacija', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(slanjePodataka)
      });

      this.evaluacija = await res.json();
    } catch (e) {
      console.error("Greška pri evaluaciji ishrane:", e);
    } finally {
      this.ucitavanje = false;
      this.cdr.detectChanges();
    }
  }
}