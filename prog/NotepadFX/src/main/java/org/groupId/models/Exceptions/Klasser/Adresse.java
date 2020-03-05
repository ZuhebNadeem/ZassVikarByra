package org.groupId.models.Exceptions.Klasser;

import java.io.Serializable;

public class Adresse implements Serializable {

    //datafelt
    public String adresse;
    public String postnr;
    public String poststed;

    //konstruktør
    public Adresse(String adresse, String postnr, String poststed) {
        this.adresse = adresse;
        this.postnr = postnr;
        this.poststed = poststed;
    }

    //Get og set
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPostnr() {
        return postnr;
    }

    public void setPostnr(String postnr) {
        this.postnr = postnr;
    }

    public String getPoststed() {
        return poststed;
    }

    public void setPoststed(String poststed) {
        this.poststed = poststed;
    }

    //toString
    @Override
    public String toString() {
        return "Adresse"+getAdresse()+" "+getPostnr()+"\n"+"Poststedet "+getPoststed()+"\n";
    }

}
