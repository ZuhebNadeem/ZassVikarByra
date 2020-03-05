package org.groupId.models.Exceptions.Klasser;

//Vi lager en klasse for en Arbeidsgiver.

import java.io.Serializable;

public class Arbeidsgiver implements Serializable {

    //datafelt
    private String bNavn;
    private String sektor;
    private Adresse bAdresse;
    private String Bransje;
    private String kStilling;//Navn og telefonnumeret vil komme fra Person.
    private Person person;

    //konstruktør
    public Arbeidsgiver(String bNavn, String sektor, Adresse bAdresse, String Bransje,String kStilling,
                        Person person) {
        this.bNavn = bNavn;
        this.sektor = sektor;
        this.bAdresse = bAdresse;
        this.Bransje = Bransje;
        this.kStilling = kStilling;
        this.person=person;
    }

    //Get og set
    public String getbNavn() {
        return bNavn;
    }

    public void setbNavn(String bNavn) {
        this.bNavn = bNavn;
    }

    public String getSektor() {
        return sektor;
    }

    public void setSektor(String sektor) {
        this.sektor = sektor;
    }

    public Adresse getbAdresse() {
        return bAdresse;
    }

    public void setbAdresse(Adresse adresse) {
        this.bAdresse = bAdresse;
    }

    public String getBransje() {
        return Bransje;
    }

    public void setBransje(String bransje) {
        this.Bransje = Bransje;
    }

    public String getkStilling() {
        return kStilling;
    }

    public void setkStilling(String stilling) {
        this.kStilling = kStilling;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }


    //toString
    @Override
    public String toString() {
        return getbNavn();
    }



}
