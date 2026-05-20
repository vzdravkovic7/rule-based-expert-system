import { Injectable } from '@angular/core';
import { KorisnikProfilDTO, TreningPlanDTO } from '../models/fittrack.dto';

@Injectable({
    providedIn: 'root'
})
export class ProfilService {
    private apiUrl = 'http://localhost:8080/api/profil';

    async generisiInicijalniPlan(profil: KorisnikProfilDTO): Promise<TreningPlanDTO> {
        const response = await fetch(`${this.apiUrl}/generisi-plan`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(profil)
        });

        if (!response.ok) {
            throw new Error(`Greška pri generisanju plana: ${response.statusText}`);
        }

        return await response.json();
    }
}