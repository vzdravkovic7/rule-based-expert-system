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

export interface PulsSimulacijaEventDTO {
    bpm: number;
    sekundiOdPocetka: number;
}

export interface CEPSimulacijaZahtevDTO {
    korisnikId: number;
    godine: number;
    pulsUMirovanju: number;
    pritisakKategorija: string;
    intenzitetTreninga: 'NIZAK' | 'UMEREN' | 'VISOK';
    aktivnaSesija: boolean;
    pulsniDogadjaji: PulsSimulacijaEventDTO[];
}

export interface CEPAlarmDTO {
    nazivPravila: string;
    nivo: 'VISOKI' | 'SREDNJI' | 'KRITICNI' | 'POTVRDA';
    poruka: string;
}

export interface CEPSimulacijaIzlazDTO {
    izracunatMaxPuls: number;
    ciljnaZonaDonja: number;
    ciljnaZonaGornja: number;
    aktiviraniAlarmi: CEPAlarmDTO[];
}

export interface BackwardZahtevDTO {
    godine: number;
    bmi: number;
    pritisakKategorija: 'NORMALAN' | 'HIPERTENZIJA_1' | 'HIPERTENZIJA_2';
    tipCilja: 'GUBITAK_MASTI' | 'MISICNA_MASA';
    ciljniProcenatMasti: number;
    kalorijskiDeficit: number;
    tipTreninga: 'KARDIO' | 'SNAGA' | 'KOMBINOVANO';
    nivoAktivnosti: 'NIZAK' | 'SREDNJI' | 'VISOK';
    dnevniProtein: number;
    promenaMasePoslednjihNedelja: number;
}

export interface BackwardOdgovorDTO {
    telesniSastavIspunjen: boolean;
    nutritivniPredusloviIspunjen: boolean;
    programBezbedan: boolean;
    vremeDoCiljaDovoljno: boolean;
    procenjenoNedeljaDoCilja: number;
    kontraindikacije: string[];
}