import { Component, ChangeDetectorRef } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ProfilService } from '../../services/profil.service';
import { KorisnikProfilDTO, TreningPlanDTO } from '../../models/fittrack.dto';

@Component({
  selector: 'app-profil-forma',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './profil-forma.html',
  styleUrls: ['./profil-forma.css']
})
export class ProfilFormaComponent {
  profil: KorisnikProfilDTO = {
    id: 1,
    ime: 'Marko',
    godine: 28,
    visina: 180,
    telesnaMasa: 95,
    sistolickiPritisak: 138,
    dijastolickiPritisak: 88,
    metMinutaNedeljno: 320,
    cilj: 'GUBITAK_MASTI'
  };

  plan: TreningPlanDTO | null = null;
  ucitavanje = false;
  greska: string | null = null;

  constructor(
    private profilService: ProfilService,
    private cdr: ChangeDetectorRef
  ) { }

  async posaljiProfil() {
    this.ucitavanje = true;
    this.greska = null;
    this.plan = null;
    this.cdr.detectChanges();

    try {
      const rezultat = await this.profilService.generisiInicijalniPlan(this.profil);

      this.plan = { ...rezultat };
    } catch (e: any) {
      this.greska = e.message || 'Došlo je do greške prilikom komunikacije sa backendom.';
    } finally {
      this.ucitavanje = false;

      this.cdr.detectChanges();
    }
  }
}