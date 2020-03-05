package org.groupId.models.Exceptions.Klasser;

import java.io.Serializable;
import java.time.LocalDate;

public class nyStilling implements Serializable {

    //datafelt
    private String annonseTittel;
    private Arbeidsgiver arbeidsgiver;
    private String arbeidssted;
    private Dato dato;//dette er både for StartDato og SluttDato
    private String jobbkategori;
    private String stilingsType;
    private String arbeidsVilkår;
    private String lønnsBetingelser;
    private String jobbTidfra;
    private String jobbTidtil;
    private String kvalifikasjoner;
    private String StillingsBskrivelse;

    private String arbNavn;
    private String arbSektor;
    private LocalDate startDatoen;
    private LocalDate sluttDatoen;
    private String kontaktnavn;
    private String kontakttelnr;
    private String kontaktstilling;



    //konstruktør
    public nyStilling(String annonseTittel, Arbeidsgiver arbeidsgiver, String arbeidssted, Dato dato, String jobbkategori, String stilingsType, String arbeidsVilkår,
                      String lønnsBetingelser, String jobbTidfra, String jobbTidtil, String kvalifikasjoner, String stillingsBskrivelse,Jobbsoker jobbsoker) {

        this.arbeidssted = arbeidssted;
        this.dato = dato;
        this.jobbkategori = jobbkategori;
        this.stilingsType = stilingsType;
        this.arbeidsVilkår = arbeidsVilkår;
        this.lønnsBetingelser = lønnsBetingelser;
        this.jobbTidfra = jobbTidfra;
        this.jobbTidtil = jobbTidtil;
        this.kvalifikasjoner = kvalifikasjoner;
        this.StillingsBskrivelse = stillingsBskrivelse;
        this.arbeidsgiver=arbeidsgiver;
        this.annonseTittel = annonseTittel;

        this.arbNavn=arbeidsgiver.getbNavn();
        this.arbSektor=arbeidsgiver.getSektor();
        this.startDatoen=dato.getFraDato();
        this.sluttDatoen=dato.getTildato();
        this.kontaktnavn=arbeidsgiver.getPerson().getFornavn();
        this.kontakttelnr=arbeidsgiver.getPerson().getTelefonnummer();
        this.kontaktstilling=arbeidsgiver.getkStilling();
    }

    //Get og set
    public String getAnnonseTittel() {
        return annonseTittel;
    }

    public void setAnnonseTittel(String annonseTittel) {
        this.annonseTittel = annonseTittel;
    }

    public Arbeidsgiver getArbeidsgiver() {
        return arbeidsgiver;
    }

    public void setArbeidsgiver(Arbeidsgiver arbeidsgiver) {
        this.arbeidsgiver = arbeidsgiver;
    }

    public String getArbNavn() {
        return arbNavn;

    }



    public void setArbNavn(String ArbNavn) {
        this.arbNavn =arbNavn;
    }


    public String getArbeidssted() {
        return arbeidssted;
    }

    public void setArbeidssted(String arbeidssted) {
        this.arbeidssted = arbeidssted;
    }

    public Dato getDato() {
        return dato;
    }

    public void setDato(Dato dato) {
        this.dato = dato;
    }

    public String getJobbkategori() {
        return jobbkategori;
    }

    public void setJobbkategori(String jobbkategori) {
        this.jobbkategori = jobbkategori;
    }

    public String getStilingsType() {
        return stilingsType;
    }

    public void setStilingsType(String stilingsType) {
        this.stilingsType = stilingsType;
    }

    public String getArbeidsVilkår() {
        return arbeidsVilkår;
    }

    public void setArbeidsVilkår(String arbeidsVilkår) {
        this.arbeidsVilkår = arbeidsVilkår;
    }

    public String getLønnsBetingelser() {
        return lønnsBetingelser;
    }

    public void setLønnsBetingelser(String lønnsBetingelser) {
        this.lønnsBetingelser = lønnsBetingelser;
    }

    public String getJobbTidtil() {
        return jobbTidtil;
    }

    public void setJobbTidtil(String jobbTidtil) {
        this.jobbTidtil = jobbTidtil;
    }

    public String getJobbTidfra() {
        return jobbTidfra;
    }

    public void setJobbTidfra(String jobbTidfra) {
        this.jobbTidfra = jobbTidfra;
    }

    public String getKvalifikasjoner() {
        return kvalifikasjoner;
    }

    public void setKvalifikasjoner(String kvalifikasjoner) {
        this.kvalifikasjoner = kvalifikasjoner;
    }

    public String getStillingsBskrivelse() {
        return StillingsBskrivelse;
    }

    public void setStillingsBskrivelse(String stillingsBskrivelse) {
        StillingsBskrivelse = stillingsBskrivelse;
    }

    public String getArbSektor() {
        return arbSektor;
    }

    public void setArbSektor(String arbSektor) {
        this.arbSektor = arbSektor;
    }
    public LocalDate getStartDatoen() {
        return startDatoen;
    }

    public void setStartDatoen(LocalDate startDatoen) {
        this.startDatoen = startDatoen;
    }


    public LocalDate getSluttDatoen() {
        return sluttDatoen;
    }

    public void setSluttDatoen(LocalDate sluttDatoen) {
        this.sluttDatoen = sluttDatoen;
    }

    public String getKontaktnavn() {
        return kontaktnavn;
    }

    public void setKontaktnavn(String kontaktnavn) {
        this.kontaktnavn = kontaktnavn;
    }

    public String getKontakttelnr() {
        return kontakttelnr;
    }

    public void setKontakttelnr(String kontakttelnr) {
        this.kontakttelnr = kontakttelnr;
    }

    public String getKontaktstilling() {
        return kontaktstilling;
    }

    public void setKontaktstilling(String kontaktstilling) {
        this.kontaktstilling = kontaktstilling;
    }

    //toString
    @Override
    public String toString() {
        return getAnnonseTittel();
    }
}