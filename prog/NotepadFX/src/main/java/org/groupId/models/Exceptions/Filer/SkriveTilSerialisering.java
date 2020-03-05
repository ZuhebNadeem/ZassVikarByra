package org.groupId.models.Exceptions.Filer;

import javafx.scene.control.Alert;
import org.groupId.models.Exceptions.UlovligFilException;
import org.groupId.models.Exceptions.Klasser.Arbeidsgiver;
import org.groupId.models.Exceptions.Klasser.Jobbsoker;
import org.groupId.models.Exceptions.Klasser.nyStilling;

import java.io.*;
import java.util.ArrayList;


public class SkriveTilSerialisering implements FilLagring {

    //Skrive til brukeren på skjermen
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @Override
    public void skrivTilFil(String path, ArrayList<Arbeidsgiver> nyArbeidsgiver, ArrayList<nyStilling> nyeStillinger, ArrayList<Jobbsoker> nyJobbsoker) throws IOException, UlovligFilException {

        File filen = new File(path);

        try{
            if(filen.exists()) {
                FileOutputStream fil = new FileOutputStream(filen);
                ObjectOutputStream outputStream = new ObjectOutputStream(fil);

                //skriver de forskjellige objektene
                outputStream.writeObject(nyArbeidsgiver);
                outputStream.writeObject(nyeStillinger);
                //outputStream.writeObject(nyJobbsoker);

                fil.close();
                outputStream.close();
            }
            else {
                throw new UlovligFilException("Filen finnes ikke");
            }
        } catch (UlovligFilException e) {
            catcher(e.getMessage());
        }

    }

    //Klasse for catcher
    public void catcher(String feilmelding) {
        alert.setHeaderText("Feilmelding!");
        alert.setContentText(feilmelding);
        alert.showAndWait();
    }
}

