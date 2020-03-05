package org.groupId.models.Exceptions.Filer;

import javafx.scene.control.Alert;
import org.groupId.models.Exceptions.UlovligFilException;
import org.groupId.models.Exceptions.Klasser.Arbeidsgiver;
import org.groupId.models.Exceptions.Klasser.Jobbsoker;
import org.groupId.models.Exceptions.Klasser.nyStilling;

import java.io.*;
import java.util.ArrayList;

//Klasse som representerer lagring til csv, skriving til csv
public class SkriverTilFiler implements FilLagring {

    //Skrive til brukeren på skjermen
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @Override
    public void skrivTilFil(String path, ArrayList<Arbeidsgiver> nyArbeidsgiver, ArrayList<nyStilling> nyeStillinger, ArrayList<Jobbsoker> nyJobbsoker) throws IOException, UlovligFilException {

        File filen = new File(path);
        PrintWriter writer = new PrintWriter(new FileWriter(filen));

        try {
            if (filen.exists()) {
                    //skrive til fil for nye arbeidsgivere
                    writer.append("Arbeidsgivere \n");
                    for (Arbeidsgiver nyeArbeidsgiver : nyArbeidsgiver) {
                        skriverNyArbeidsgiver(writer, nyeArbeidsgiver);
                    }

                    //skrive til fil for å legge ut stilling
                    writer.append("Legg ut stilling \n");
                    for (nyStilling nyLedigStilling : nyeStillinger) {
                        skriverNyStilling(writer, nyLedigStilling);
                    }

                    //skriv til fil for Jobbsøker
                    writer.append("Jobbsøker \n");
                    for (Jobbsoker nyeJobbsokeren : nyJobbsoker) {
                        skriverNyJobbsoker(writer, nyeJobbsokeren);
                    }

                }

            else {
                throw new UlovligFilException("Filen finnes ikke");
            }
        } catch (UlovligFilException e) {
            catcher(e.getMessage());;
        }
       finally { // dette skjer uavhengende
            try {
                writer.flush();
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void skriverNyArbeidsgiver(PrintWriter writer, Arbeidsgiver arbeidsgiver) {
        writer.append(arbeidsgiver.getbNavn());
        writer.append(";");
        writer.append(arbeidsgiver.getSektor());
        writer.append(";");
        writer.append(arbeidsgiver.getbAdresse().getAdresse());
        writer.append(";");
        writer.append(arbeidsgiver.getbAdresse().getPostnr());
        writer.append(";");
        writer.append(String.valueOf(arbeidsgiver.getbAdresse().getPoststed()));
        writer.append(";");
        writer.append(arbeidsgiver.getBransje());
        writer.append(";");
        writer.append(arbeidsgiver.getkStilling());
        writer.append(";");
        writer.append(arbeidsgiver.getPerson().getFornavn());
        writer.append(";");
        writer.append(arbeidsgiver.getPerson().getTelefonnummer());
        writer.append("\n");
    }

    public void skriverNyStilling(PrintWriter writer, nyStilling ny) {
        writer.append(ny.getAnnonseTittel());
        writer.append(";");
        writer.append(ny.getArbeidsgiver().getbNavn());
        writer.append(";");
        writer.append(ny.getArbeidssted());
        writer.append(";");
        writer.append(String.valueOf(ny.getDato().getFraDato()));
        writer.append(";");
        writer.append(String.valueOf(ny.getDato().getTildato()));
        writer.append(";");
        writer.append(ny.getJobbkategori());
        writer.append(";");
        writer.append(ny.getStilingsType());
        writer.append(";");
        writer.append(ny.getArbeidsVilkår());
        writer.append(";");
        writer.append(ny.getLønnsBetingelser());
        writer.append(";");
        writer.append(ny.getJobbTidfra());
        writer.append(";");
        writer.append(ny.getJobbTidtil());
        writer.append(";");
        writer.append(ny.getKvalifikasjoner());
        writer.append(";");
        writer.append(ny.getStillingsBskrivelse());
        writer.append("\n");
    }



    public void skriverNyJobbsoker(PrintWriter writer, Jobbsoker nyJobbsoker) {
        writer.append(nyJobbsoker.getPerson().getFornavn());
        writer.append(";");
        writer.append(nyJobbsoker.getPerson().getTelefonnummer());
        writer.append(";");
        writer.append(nyJobbsoker.getEpost());
        writer.append(";");
        writer.append(String.valueOf(nyJobbsoker.getFødselsDato()));
        writer.append(";");
        writer.append(nyJobbsoker.getAdresse().getAdresse());
        writer.append(";");
        writer.append(nyJobbsoker.getAdresse().getPostnr());
        writer.append(";");
        writer.append(String.valueOf(nyJobbsoker.getAdresse().getPoststed()));
        writer.append(";");
        writer.append(nyJobbsoker.getUtdannelse());
        writer.append(";");
        writer.append(nyJobbsoker.getTypeutdanning());
        writer.append(";");
        writer.append(nyJobbsoker.getLønnskrav());
        writer.append(";");

        for (int i = 0; i < nyJobbsoker.getReferanser().size(); i++) {
            writer.append(nyJobbsoker.getReferanser().get(i).getFornavn());
            writer.append(";");
            writer.append(nyJobbsoker.getReferanser().get(i).getTelefonnummer());
            writer.append(";");
        }
        for (int i = 0; i < nyJobbsoker.getJobberfaring().size(); i++) {
            writer.append(nyJobbsoker.getJobberfaring().get(i).getFirma());
            writer.append(";");
            writer.append(nyJobbsoker.getJobberfaring().get(i).getStilling());
            writer.append(";");
            writer.append(String.valueOf(nyJobbsoker.getJobberfaring().get(i).getFraDatoErfaring()));
            writer.append(";");
            writer.append(String.valueOf(nyJobbsoker.getJobberfaring().get(i).getTilDatoErfaring()));
            writer.append(";");
        }
        writer.append("\n");
    }

    //Klasse for catcher
    public void catcher(String feilmelding) {
        alert.setHeaderText("Feilmelding!");
        alert.setContentText(feilmelding);
        alert.showAndWait();
    }
 }




