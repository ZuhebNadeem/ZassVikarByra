package org.groupId.models.Exceptions.Klasser;

import java.io.Serializable;
import java.time.LocalDate;


public class Dato implements Serializable {

    //datafelt
    private LocalDate fraDato;
    private LocalDate tildato;

    //konstruktør
    public Dato(LocalDate fraDato, LocalDate tildato) {
        this.fraDato = fraDato;
        this.tildato = tildato;

    }

    //Get og set
    public LocalDate getFraDato()
    {
        return fraDato;
    }

    public void setFraDato(LocalDate fraDato) {
        this.fraDato = fraDato;
    }

    public LocalDate getTildato() {
        return tildato;
    }

    public void setTildato(LocalDate tildato) {
        this.tildato = tildato;
    }

    //toString
    @Override
    public String toString() {
        return "Du vil arbeide fra "+getFraDato()+" frem til "+getTildato()+" ";
    }
}
