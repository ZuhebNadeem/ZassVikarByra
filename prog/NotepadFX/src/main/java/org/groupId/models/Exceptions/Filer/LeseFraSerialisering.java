package org.groupId.models.Exceptions.Filer;

import javafx.scene.control.Alert;
import org.groupId.models.Exceptions.UlovligFilException;
import org.groupId.models.Exceptions.Klasser.Arbeidsgiver;
import org.groupId.models.Exceptions.Klasser.Jobbsoker;
import org.groupId.models.Exceptions.Klasser.nyStilling;

import java.io.*;
import java.util.ArrayList;

public class LeseFraSerialisering implements LesFraFil {

    //Skrive til brukeren på skjermen
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @Override
    public void LesFraFile(String path, ArrayList<Arbeidsgiver> nyArbeidsgiver, ArrayList<nyStilling> nyeStillinger, ArrayList<Jobbsoker> nyJobbsoker) throws IOException, UlovligFilException {

        File filen = new File(path);

        try {
            if (filen.exists()) {
                FileInputStream fileInputStream = new FileInputStream(filen);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);

                ArrayList<Arbeidsgiver> nyeeArbeidsgiver = (ArrayList<Arbeidsgiver>) in.readObject();

                for (Arbeidsgiver giver : nyeeArbeidsgiver) {
                    nyArbeidsgiver.add(giver);
                }

                ArrayList<nyStilling> nyStillingen = (ArrayList<nyStilling>) in.readObject();

                for (nyStilling giver : nyStillingen) {
                    nyeStillinger.add(giver);
                }

           /* ArrayList<Jobbsoker> jobbsokeren = (ArrayList<Jobbsoker>) in.readObject();

            for (Jobbsoker giver : jobbsokeren) {
                nyJobbsoker.add(giver);
            }*/

                in.close();
                fileInputStream.close();
            } else {
                throw new UlovligFilException("Filen finnes ikke");
            }

        } catch (UlovligFilException e) {
            catcher(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Klasse for catcher
    public void catcher(String feilmelding) {
        alert.setHeaderText("Feilmelding!");
        alert.setContentText(feilmelding);
        alert.showAndWait();
    }

}

