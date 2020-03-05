package org.groupId.models.Exceptions.Klasser;

import java.io.Serializable;

public class Person implements Serializable {

     //datafelt
    private String fornavn;
    private String telefonnummer;

     //konstruktør
    public Person(String fornavn, String telefonnummer) {
        this.fornavn = fornavn;
        this.telefonnummer = telefonnummer;
    }

     //Get og set
     public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }


    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {

        this.telefonnummer = telefonnummer;
    }


    public String toString() {
        return getFornavn() + getTelefonnummer() + "\n";

    }
}