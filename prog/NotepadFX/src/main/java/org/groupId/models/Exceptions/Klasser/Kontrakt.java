package org.groupId.models.Exceptions.Klasser;

import java.time.LocalDate;

public class Kontrakt {
    private nyStilling nyStilling;
    private Jobbsoker jobbsoker;
    private String navnJobbsoker;
    private String navnArbeidsgiver;
    private String navnAnonnsetittel;
    private LocalDate kontraktSTdato;
    private LocalDate kontraktSLdato;

    public Kontrakt(nyStilling nyStilling, Jobbsoker jobbsoker) {
        this.nyStilling = nyStilling;
        this.jobbsoker = jobbsoker;
        this.navnJobbsoker=jobbsoker.getPerson().getFornavn();
        this.navnArbeidsgiver=nyStilling.getArbNavn();
        this.navnAnonnsetittel=nyStilling.getAnnonseTittel();
        this.kontraktSTdato=nyStilling.getStartDatoen();
        this.kontraktSLdato=nyStilling.getSluttDatoen();


    }

    public nyStilling getNyStilling() {
        return nyStilling;
    }

    public void setNyStilling(nyStilling nyStilling) {
        this.nyStilling = nyStilling;
    }

    public Jobbsoker getJobbsoker() {
        return jobbsoker;
    }

    public void setJobbsoker(Jobbsoker jobbsoker) {
        this.jobbsoker = jobbsoker;
    }

    public String getNavnJobbsoker() {
        return navnJobbsoker;
    }

    public void setNavnJobbsoker(String navnJobbsoker) {
        this.navnJobbsoker = navnJobbsoker;
    }
    public String getNavnArbeidsgiver() {
        return navnArbeidsgiver;
    }

    public void setNavnArbeidsgiver(String navnArbeidsgiver) {
        this.navnArbeidsgiver = navnArbeidsgiver;
    }

    public String getNavnAnonnsetittel() {
        return navnAnonnsetittel;
    }

    public void setNavnAnonnsetittel(String navnAnonnsetittel) {
        this.navnAnonnsetittel = navnAnonnsetittel;
    }

    public LocalDate getKontraktSTdato() {
        return kontraktSTdato;
    }

    public void setKontraktSTdato(LocalDate kontraktSTdato) {
        this.kontraktSTdato = kontraktSTdato;
    }

    public LocalDate getKontraktSLdato() {
        return kontraktSLdato;
    }

    public void setKontraktSLdato(LocalDate kontraktSLdato) {
        this.kontraktSLdato = kontraktSLdato;
    }



}
