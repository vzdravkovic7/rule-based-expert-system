import { Injectable } from '@angular/core';
import { NutricijaEvaluacijaDTO } from '../models/fittrack.dto';

@Injectable({
    providedIn: 'root'
})
export class NutricijaService {
    private apiUrl = 'http://localhost:8080/api/nutricija';

    async evaluirajNutriciju(evaluacija: NutricijaEvaluacijaDTO): Promise<NutricijaEvaluacijaDTO> {
        const response = await fetch(`${this.apiUrl}/evaluacija`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(evaluacija)
        });

        if (!response.ok) {
            throw new Error(`Greška pri evaluaciji nutricije: ${response.statusText}`);
        }

        return await response.json();
    }
}