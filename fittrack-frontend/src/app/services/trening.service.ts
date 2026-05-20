import { Injectable } from '@angular/core';
import { UnosTreningaDTO, TreningPlanDTO } from '../models/fittrack.dto';

@Injectable({
    providedIn: 'root'
})
export class TreningService {
    private apiUrl = 'http://localhost:8080/api/trening';

    async proveriProgresiju(unos: UnosTreningaDTO): Promise<TreningPlanDTO> {
        const response = await fetch(`${this.apiUrl}/progresija`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(unos)
        });

        if (!response.ok) {
            throw new Error(`Greška pri proveri progresije: ${response.statusText}`);
        }

        return await response.json();
    }
}