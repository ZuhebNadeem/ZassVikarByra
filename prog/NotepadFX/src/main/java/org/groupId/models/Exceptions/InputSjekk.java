package org.groupId.models.Exceptions;
import javafx.scene.control.*;
import java.time.ZoneId;
import java.util.Date;

public class InputSjekk {

    //Skrive til brukeren på skjermen
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    Boolean riktig = true;

    //Feilmelding-metoder

    // Vi bruker denne for bokstaver(TextField) og om feltet ikke er bokstaver kastes feilmeldingen.
    public boolean regexSjekkBokstaver(TextField felt) {
        try {
            if (!felt.getText().matches("[a-zæøåA-ZÆØÅ ]{1,50}")) {
                felt.setStyle("-fx-background-color: #ff0021");
                riktig = false;
                throw new UlovligStringException("Du må skrive bokstaver på feltet: " + felt.getPromptText());
            } else {
                felt.setStyle("-fx-background-color: #FFFFFF");
                riktig = true;
            }
        } catch (UlovligStringException e) {
            catcher(e.getMessage());
        }
        return riktig;
    }

    // Vi bruker denne for bokstaver(TextArea) og om feltet ikke er bokstaver kastes feilmeldingen.
    public boolean regexSjekkerBokstaver(TextArea felt) {
        try {
            if (!felt.getText().matches("[a-zæøåA-ZÆØÅ 0-9 \\.\\,\\-\\/]{1,100}")) {
                felt.setStyle("-fx-background-color: #ff0021");
                riktig = false;
                throw new UlovligStringException("Du må skrive bokstaver på felt: " + felt.getPromptText());
            } else {
                felt.setStyle("-fx-background-color: #FFFFFF");
                riktig = true;
            }
        } catch (UlovligStringException e) {
            catcher(e.getMessage());
        }
        return riktig;
    }


    // Vi bruker denne for tall og om feltet ikke er tall kastes feilmeldingen.
    public boolean regexSjekkTall(TextField felt) {
        try {
            if (!felt.getText().matches("[0-9 ]{1,50}")) {
                felt.setStyle("-fx-background-color: #ff0021");
                riktig = false;
                throw new UlovligTallException("Du må skrive tall på feltet: " + felt.getPromptText());
            } else {
                felt.setStyle("-fx-background-color: #FFFFFF");
                riktig = true;
            }
        } catch (UlovligTallException e) {
            catcher(e.getMessage());
        }
        return riktig;
    }

    // Vi bruker denne for dato og om feltet ikke inneholder dato kastes feilmeldingen.
    public boolean regexSjekkDato(DatePicker StartDato, DatePicker SluttDato) {
        try {
            //Datofeltene må inneholde verdier
            if ((StartDato.getValue() == null) || (SluttDato.getValue() == null)) {
                StartDato.setStyle("-fx-background-color: #d31414");
                SluttDato.setStyle("-fx-background-color: #d31414");
                riktig = false;
                throw new UlovligDatoException("Datofeltene er tomme på: " + StartDato.getPromptText() + "eller" + SluttDato.getPromptText());
            } else {    //for å gjøre LocalDate til Date
                Date StartDatoen = java.util.Date.from(StartDato.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                Date SluttDatoen = java.util.Date.from(SluttDato.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

                //StartDatoen kan ikke være før dagens dato
                if (!StartDatoen.after(new Date())) {
                    StartDato.setStyle("-fx-background-color: #d31414");
                    riktig = false;
                    throw new UlovligDatoException("Stardatoen kan ikke være gammel dato: " + StartDato.getPromptText());
                }
                //StartDato må være før sluttdato
                if (!StartDatoen.before(SluttDatoen)) {
                    StartDato.setStyle("-fx-background-color: #d31414");
                    SluttDato.setStyle("-fx-background-color: #d31414");
                    riktig = false;
                    throw new UlovligDatoException("Startdatoen må være større enn sluttdatoen på: " + StartDato.getPromptText());
                } else {
                    riktig = true;
                    StartDato.setStyle("-fx-background-color: #7CFC00");
                    SluttDato.setStyle("-fx-background-color: #7CFC00");
                }
            }
        } catch (UlovligDatoException e) {
            catcher(e.getMessage());
        }
        return riktig;
    }


    // Vi bruker denne for tid og om feltet ikke er riktig tid kastes feilmeldingen.
    public boolean regexSjekkTid(TextField felt) {
        try {
            if (!felt.getText().matches("(0[0-9]|1[0-9]|2[0-3]|[0-9]):[0-5][0-9]")) {
                felt.setStyle("-fx-background-color: #ff0021");
                riktig = false;
                throw new UlovligStringException("Du må skrive tid i riktig format(HH:MM): " + felt.getPromptText());
            } else {
                felt.setStyle("-fx-background-color: #FFFFFF");
                riktig = true;
            }
        } catch (UlovligStringException e) {
            catcher(e.getMessage());
        }
        return riktig;
    }

    // Vi bruker denne for postnr og om feltet ikke inneholder eksakt 4 tall kastes feilmeldingen.
    public boolean regexSjekkPostnr(TextField felt) {
        try {
            if (!felt.getText().matches("[0-9]{4}")) {
                felt.setStyle("-fx-background-color: #ff0021");
                riktig = false;
                throw new UlovligStringException("Du kan kun skrive 4 tall på feltet: " + felt.getPromptText());
            } else {
                felt.setStyle("-fx-background-color: #FFFFFF");
                riktig = true;
            }
        } catch (UlovligStringException e) {
            catcher(e.getMessage());
        }
        return riktig;
    }

    // Vi bruker denne for telefonnummer og om feltet ikke inneholder 8 tall kastes feilmeldingen.
    public boolean regexSjekkTlfnr(TextField felt) {
        try {
            if (!felt.getText().matches("[0-9 ]{8}")) {
                felt.setStyle("-fx-background-color: #ff0021");
                riktig = false;
                throw new UlovligStringException("Du kan kun skrive 8 tall på feltet: " + felt.getPromptText());
            } else {
                felt.setStyle("-fx-background-color: #FFFFFF");
                riktig = true;
            }
        } catch (UlovligStringException e) {
            catcher(e.getMessage());
        }
        return riktig;
    }

    // Vi bruker denne for Fødselsdato og om feltet ikke inneholder dato kastes feilmeldingen.
    public boolean regexSjekkFødselsDato(DatePicker Fødselsdato) {
        try {
            //Datofeltene må inneholde verdi
            if ((Fødselsdato.getValue() == null)) {
                Fødselsdato.setStyle("-fx-background-color: #d31414");
                riktig = false;
                throw new UlovligDatoException("Datofeltet er tom: " + Fødselsdato.getPromptText());
            } else {    //for å gjøre LocalDate til Date
                Date Fødselsdatoen = java.util.Date.from(Fødselsdato.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

                //Fødselsdatoen kan ikke være etter dagens dato
                if (!Fødselsdatoen.before(new Date())) {
                    Fødselsdato.setStyle("-fx-background-color: #d31414");
                    riktig = false;
                    throw new UlovligDatoException("Fødselsdatoen kan ikke være nyere dato: " + Fødselsdato.getPromptText());
                } else {
                    riktig = true;
                    Fødselsdato.setStyle("-fx-background-color: #7CFC00");
                }
            }
        } catch (UlovligDatoException e) {
            catcher(e.getMessage());
        }
        return riktig;
    }


    // Vi bruker denne for jobberfaringDato og om feltet ikke inneholder dato kastes feilmeldingen.
    public boolean regexSjekkJobbDato(DatePicker JobberfaringFDato, DatePicker JobberfaringTdato) {
        try {
            //Datofeltene må inneholde verdier
            if ((JobberfaringTdato.getValue() == null) || (JobberfaringTdato.getValue() == null)) {
                JobberfaringFDato.setStyle("-fx-background-color: #d31414");
                JobberfaringTdato.setStyle("-fx-background-color: #d31414");
                riktig = false;
                throw new UlovligDatoException("Datofeltene er tomme på: " + JobberfaringFDato.getPromptText() + " " + JobberfaringTdato.getPromptText());
            } else {    //for å gjøre LocalDate til Date
                Date JobberfaringFDatoen = java.util.Date.from(JobberfaringFDato.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                Date JobberfaringTdatoen = java.util.Date.from(JobberfaringTdato.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

                //StartDatoen kan ikke være etter dagens dato
                if (!JobberfaringFDatoen.before(new Date())) {
                    JobberfaringFDato.setStyle("-fx-background-color: #d31414");
                    riktig = false;
                    throw new UlovligDatoException("Fradato kan ikke være nyere dato: " + JobberfaringFDato.getPromptText());
                }
                //StartDato må være før sluttdato
                if (!JobberfaringFDatoen.before(JobberfaringTdatoen)) {
                    JobberfaringFDato.setStyle("-fx-background-color: #d31414");
                    JobberfaringTdato.setStyle("-fx-background-color: #d31414");
                    riktig = false;
                    throw new UlovligDatoException("Fradato må være større enn Tildato på: " + JobberfaringFDato.getPromptText());
                } else {
                    riktig = true;
                    JobberfaringFDato.setStyle("-fx-background-color: #7CFC00");
                    JobberfaringTdato.setStyle("-fx-background-color: #7CFC00");
                }
            }
        } catch (UlovligDatoException e) {
            catcher(e.getMessage());
        }
        return riktig;
    }

    // Vi bruker denne for Email(TextField) og om feltet ikke er email kastes feilmeldingen.
    public boolean regexSjekkEmail(TextField felt) {
        try {
            if (!felt.getText().matches("^\\w?([a-zA-ZæøåÆØÅ])+([.-]?\\w+)*@\\w?([a-zA-ZæøåÆØÅ])+([.-]?\\w+)*(\\.\\w{2,3})+$")) {
                felt.setStyle("-fx-background-color: #ff0021");
                riktig = false;
                throw new UlovligEmailException("Du må skrive riktig email på feltet: " + felt.getPromptText());
            } else {
                felt.setStyle("-fx-background-color: #FFFFFF");
                riktig = true;
            }
        } catch (UlovligEmailException e) {
            catcher(e.getMessage());
        }
        return riktig;
    }

    // Vi bruker denne for Adresse(TextField) og om feltet ikke er bokstaver eller tall kastes feilmeldingen.
    public boolean regexSjekkAdresse(TextField felt) {
        try {
            if (!felt.getText().matches("[a-zæøåA-ZÆØÅ 0-9]{1,100}")) {
                felt.setStyle("-fx-background-color: #ff0021");
                riktig = false;
                throw new UlovligStringException("Du må skrive bokstaver på felt: " + felt.getPromptText());
            } else {
                felt.setStyle("-fx-background-color: #FFFFFF");
                riktig = true;
            }
        } catch (UlovligStringException e) {
            catcher(e.getMessage());
        }
        return riktig;
    }


    //Klasse for catcher
    public void catcher(String feilmelding) {
        alert.setHeaderText("Feilmelding!");
        alert.setContentText(feilmelding);
        alert.showAndWait();
    }

}