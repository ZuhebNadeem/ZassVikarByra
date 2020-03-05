package org.groupId.models.Exceptions.Klasser;

import java.time.LocalDate;

public class Jobberfaring {

    private String stilling;
    private String firma;
    private LocalDate fraDatoErfaring;
    private LocalDate tilDatoErfaring;

    public Jobberfaring(String stilling, String firma, LocalDate fraDatoErfaring, LocalDate tilDatoErfaring) {
        this.stilling = stilling;
        this.firma = firma;
        this.fraDatoErfaring = fraDatoErfaring;
        this.tilDatoErfaring = tilDatoErfaring;
    }

    public String getStilling() {
        return stilling;
    }

    public void setStilling(String stilling) {
        this.stilling = stilling;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public LocalDate getFraDatoErfaring() {
        return fraDatoErfaring;
    }

    public void setFraDatoErfaring(LocalDate fraDatoErfaring) {
        this.fraDatoErfaring = fraDatoErfaring;
    }

    public LocalDate getTilDatoErfaring() {
        return tilDatoErfaring;
    }

    public void setTilDatoErfaring(LocalDate tilDatoErfaring) {
        this.tilDatoErfaring = tilDatoErfaring;
    }



}
