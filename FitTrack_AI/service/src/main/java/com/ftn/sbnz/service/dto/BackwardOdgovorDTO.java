package com.ftn.sbnz.service.dto;

import java.util.List;

public class BackwardOdgovorDTO {
    private boolean telesniSastavIspunjen;
    private boolean nutritivniPredusloviIspunjen;
    private boolean programBezbedan;
    private boolean vremeDoCiljaDovoljno;
    private int procenjenoNedeljaDoCilja;
    private List<String> kontraindikacije;

    public BackwardOdgovorDTO() {
    }

    public BackwardOdgovorDTO(boolean telesniSastavIspunjen, boolean nutritivniPredusloviIspunjen,
            boolean programBezbedan, boolean vremeDoCiljaDovoljno,
            int procenjenoNedeljaDoCilja, List<String> kontraindikacije) {
        this.telesniSastavIspunjen = telesniSastavIspunjen;
        this.nutritivniPredusloviIspunjen = nutritivniPredusloviIspunjen;
        this.programBezbedan = programBezbedan;
        this.vremeDoCiljaDovoljno = vremeDoCiljaDovoljno;
        this.procenjenoNedeljaDoCilja = procenjenoNedeljaDoCilja;
        this.kontraindikacije = kontraindikacije;
    }

    public boolean isTelesniSastavIspunjen() {
        return telesniSastavIspunjen;
    }

    public void setTelesniSastavIspunjen(boolean telesniSastavIspunjen) {
        this.telesniSastavIspunjen = telesniSastavIspunjen;
    }

    public boolean isNutritivniPredusloviIspunjen() {
        return nutritivniPredusloviIspunjen;
    }

    public void setNutritivniPredusloviIspunjen(boolean nutritivniPredusloviIspunjen) {
        this.nutritivniPredusloviIspunjen = nutritivniPredusloviIspunjen;
    }

    public boolean isProgramBezbedan() {
        return programBezbedan;
    }

    public void setProgramBezbedan(boolean programBezbedan) {
        this.programBezbedan = programBezbedan;
    }

    public boolean isVremeDoCiljaDovoljno() {
        return vremeDoCiljaDovoljno;
    }

    public void setVremeDoCiljaDovoljno(boolean vremeDoCiljaDovoljno) {
        this.vremeDoCiljaDovoljno = vremeDoCiljaDovoljno;
    }

    public int getProcenjenoNedeljaDoCilja() {
        return procenjenoNedeljaDoCilja;
    }

    public void setProcenjenoNedeljaDoCilja(int procenjenoNedeljaDoCilja) {
        this.procenjenoNedeljaDoCilja = procenjenoNedeljaDoCilja;
    }

    public List<String> getKontraindikacije() {
        return kontraindikacije;
    }

    public void setKontraindikacije(List<String> kontraindikacije) {
        this.kontraindikacije = kontraindikacije;
    }
}