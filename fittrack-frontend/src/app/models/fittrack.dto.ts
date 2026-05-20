export interface KorisnikProfilDTO {
    id: number;
    ime: string;
    godine: number;
    visina: number;
    telesnaMasa: number;
    sistolickiPritisak: number;
    dijastolickiPritisak: number;
    metMinutaNedeljno: number;
    cilj: 'GUBITAK_MASTI' | 'HIPERTROFIJA' | 'OPSTA_KONDICIJA';
}

export interface TreningPlanDTO {
    isSenior: boolean;
    intenzitetTreninga: string;
    nivoAktivnosti: string;
    pritisakKategorija: string;
    bmiKategorija: string;
    kardioDani: number;
    snagaDani: number;
    serijeSnaga: number;
    ponavljanjaSnaga: string;
    odmorSekundi: number;
    kardioMinuta: number;
    selektovaneVezbe: string[];
    porukaProgresa?: string;
}

export interface LogStavkaDTO {
    rpe: number;
    datum: string;
}

export interface UnosTreningaDTO {
    korisnikId: number;
    logovi: LogStavkaDTO[];
}

export interface NutricijaEvaluacijaDTO {
    korisnikId: number;
    pol: 'MUSKI' | 'ZENSKI';
    cilj: 'HIPERTROFIJA' | 'GUBITAK_MASTI';
    uneteKalorije: number;
    unetiProteini: number;
    ciljaneKalorije?: number;
    proteiniGrama?: number;
    mastiGrama?: number;
    ugljeniHidratiGrama?: number;
    komentarUnosa?: string;
}

export interface NutritivniPlanDTO {
    bmr: number;
    tdee: number;
    ciljaneKalorije: number;
    proteiniGrama: number;
    mastiGrama: number;
    ugljeniHidratiGrama: number;
    komentarUnosa: string;
}

export interface TreningLog {
    korisnikId: number;
    rpe: number;
    obradjen: boolean;
    datum: string;
}