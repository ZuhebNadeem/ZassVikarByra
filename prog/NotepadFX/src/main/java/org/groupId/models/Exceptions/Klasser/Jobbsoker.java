package org.groupId.models.Exceptions.Klasser;

import javafx.collections.ObservableList;
import java.time.LocalDate;

public class Jobbsoker {

    //datafelt
    private Person person;
    private String epost;
    private LocalDate fødselsDato;
    private Adresse adresse;
    private String utdannelse;
    private String typeutdanning;
    private String lønnskrav;
    private ObservableList<Person> referanser;
    private ObservableList<Jobberfaring>jobberfaring;


    //konstruktør
    public Jobbsoker(Person person, String epost, LocalDate fødselsDato,
                     Adresse adresse, String utdannelse, String typeutdanning,
                     String lønnskrav, ObservableList<Person>referanser,
                     ObservableList<Jobberfaring>jobberfaring) {
        this.person = person;
        this.epost = epost;
        this.fødselsDato = fødselsDato;
        this.adresse = adresse;
        this.utdannelse = utdannelse;
        this.typeutdanning = typeutdanning;
        this.lønnskrav = lønnskrav;
        this.referanser=referanser;
        this.jobberfaring=jobberfaring;
    }

    //Getter og setter

    public Person returntest(){
        return referanser.get(0);
    }
    public ObservableList<Person> getReferanser() {
        return referanser;
    }

    public void setReferanser(ObservableList<Person> referanser) {
        this.referanser = referanser;
    }

    public ObservableList<Jobberfaring> getJobberfaring() {
        return jobberfaring;
    }

    public void setJobberfaring(ObservableList<Jobberfaring> jobberfaring) {
        this.jobberfaring = jobberfaring;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public LocalDate getFødselsDato() {
        return fødselsDato;
    }

    public void setFødselsDato(LocalDate fødselsDato) {
        this.fødselsDato = fødselsDato;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }


    public String getUtdannelse() {
        return utdannelse;
    }

    public void setUtdannelse(String utdannelse) {
        this.utdannelse = utdannelse;
    }

    public String getTypeutdanning() {
        return typeutdanning;
    }

    public void setTypeutdanning(String typeutdanning) {
        this.typeutdanning = typeutdanning;
    }

    public String getLønnskrav() {
        return lønnskrav;
    }

    public void setLønnskrav(String lønnskrav) {
        this.lønnskrav = lønnskrav;
    }

    //toString
    @Override
    public String toString() {
        return person.getFornavn();
    }

}
