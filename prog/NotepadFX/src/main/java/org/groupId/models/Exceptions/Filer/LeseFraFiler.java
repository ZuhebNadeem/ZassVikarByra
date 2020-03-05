package org.groupId.models.Exceptions.Filer;

import javafx.scene.control.Alert;
import org.groupId.models.Exceptions.Klasser.*;
import org.groupId.models.Exceptions.SkriverIkkeTIlFilException;
import org.groupId.models.Exceptions.UlovligFilException;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class LeseFraFiler implements LesFraFil {

    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @Override
    public void LesFraFile(String path, ArrayList<Arbeidsgiver> nyArbeidsgiver, ArrayList<nyStilling> nyeStillinger, ArrayList<Jobbsoker> nyJobbsoker) throws IOException, UlovligFilException, SkriverIkkeTIlFilException {

        BufferedReader leser=null;
        leser = new BufferedReader(new FileReader(path)); //åpner filen for å lese fra den

        String linje = ""; //start linjen helt øverst i filen
        File filen = new File(path);

        try {
            if (filen.exists()) {
                //fortsett, les fra filen

                while ((linje = leser.readLine()) != null) {
                    String split[] = linje.split(";"); //der man finner kolon blir linjen splittet

                    //for å lese inn verdiene for Arbeidsgiver
                    if(split.length==9) {
                        Adresse ad = new Adresse((split[2]),(split[3]),(split[4]));
                        Person pers = new Person(split[7],(split[8]));

                        Arbeidsgiver arbeidsgiveren = new Arbeidsgiver((split[0]),(split[1]),ad,(split[5]),(split[6]),pers);
                        nyArbeidsgiver.add(arbeidsgiveren);
                    }

                    //for å lese inn verdiene for Legg ut stilling
                    if (split.length == 13) {
                        Arbeidsgiver arbeidsgiver2 = new Arbeidsgiver(split[1],"",null,"","",null);

                        String spliteer = ((split[3]));
                        String splitter2 = (split[4]);
                        LocalDate localDate = LocalDate.parse(spliteer);
                        LocalDate localDate2 = LocalDate.parse(splitter2);
                        Dato dato = new Dato(localDate,localDate2);

                        nyStilling lagtUtStilling = new nyStilling((split[0]),arbeidsgiver2,(split[2]),dato,(split[5]),
                                (split[6]),(split[7]),(split[8]),(split[9]),(split[10]),(split[11]),(split[12]),null);
                        nyeStillinger.add(lagtUtStilling);
                    }

                    //for å lese inn verdier fra jobbsøker
                    if(split.length==10)  {
                        Person nyPerson = new Person((split[0]),(split[1]));

                        String spliteer3 = ((split[3]));
                        LocalDate localDate3 = LocalDate.parse(spliteer3);

                        Adresse adressen = new Adresse((split[4]),(split[5]),(split[6]));

                       /*ObservableList<Person> pers = FXCollections.observableArrayList();

                        Person nyReferanse = new Person((split[11]),(split[12]));
                        pers.add(nyReferanse);

                        ObservableList<Jobberfaring> jobb = FXCollections.observableArrayList();
                        String spliteer = ((split[16]));
                        String splitter2 = (split[17]);
                        LocalDate localDateJobb = LocalDate.parse(spliteer);
                        LocalDate localDateJobb2 = LocalDate.parse(splitter2);
                        Jobberfaring jobberfaring = new Jobberfaring((split[14]),(split[15]),localDateJobb,localDateJobb2);
                        jobb.add(jobberfaring)

                        Person nyReferanse = new Person("Zuheb","46711239");
                        LocalDate start = LocalDate.of(2015,12,02);
                        LocalDate slutt =  LocalDate.of(2020,12,02);
                        Jobberfaring jobberfaring = new Jobberfaring("Sjef","OSlomet",start,slutt)*/

                        Jobbsoker soker = new Jobbsoker(nyPerson,split[2],localDate3,adressen,(split[7]),
                                (split[8]),(split[9]),null,null);
                        nyJobbsoker.add(soker);
                    }
                }

            }
            else {
                throw new UlovligFilException("Finner ikke filen");
            }
        }
        catch(UlovligFilException e){
            catcher(e.getMessage());
        }

        finally {
            if (leser != null) {
                leser.close();
            }
        }
    }


    //Klasse for catcher
    public void catcher (String feilmelding){
        alert.setHeaderText("Feilmelding!");
        alert.setContentText(feilmelding);
        alert.showAndWait();
    }


}


