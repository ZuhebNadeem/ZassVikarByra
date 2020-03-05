package org.groupId.controllers;

import org.groupId.models.Exceptions.UlovligFilException;
import org.groupId.models.Exceptions.Filer.LeseFraFiler;
import org.groupId.models.Exceptions.Filer.LeseFraSerialisering;
import org.groupId.models.Exceptions.Filer.SkriveTilSerialisering;
import org.groupId.models.Exceptions.Filer.SkriverTilFiler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.groupId.models.Exceptions.InputSjekk;
import org.groupId.models.Exceptions.SkriverIkkeTIlFilException;
import org.groupId.models.Exceptions.Klasser.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class Start implements Initializable {

	//datafelt
	private String sokt;

	private InputSjekk sjekkFelt = new InputSjekk();//Kaller på klassen , er for regexsjekk

	SkriverTilFiler skriverTilFil = new SkriverTilFiler();//Kaller på klassen, skrive til filer csv

	SkriveTilSerialisering skrivTilSerialisering = new SkriveTilSerialisering();//Kaller på klassen, skrive til filer jobj

	LeseFraFiler lesFraFil = new LeseFraFiler(); //Kaller på klassen, leser fra filer csv

	LeseFraSerialisering lesFraFilSerialisering = new LeseFraSerialisering(); //Kaller på klassen, leser fra filer jobj

	Alert alert = new Alert(Alert.AlertType.INFORMATION);

	//felter for å lagre til Fil,arrays

	ArrayList<Arbeidsgiver> arbedsgiverFil = new ArrayList<>();

	ArrayList<nyStilling> nyStillingFil = new ArrayList<>();

	ArrayList<Jobbsoker> jobbsøkerFil = new ArrayList<>();


	//fra SceneBuilder

	//Arbeidsgiver

	@FXML
	private TextField bNavn, bAdresse, bPostnr, bPoststed, kStilling, kTlfnr, kFornavn;

	@FXML
	private ComboBox Bransje;

	@FXML
	private ObservableList<String> Bransjelister;

	@FXML
	private Label registrertArbeidsgiver;

	@FXML
	private ToggleGroup Asektor;

	@FXML
	private RadioButton sOffentlig, sPrivat;

	@FXML
	private ListView<Arbeidsgiver> lagtarbeidsgivere;

	@FXML
	private ObservableList<Arbeidsgiver> allearbeidsgivere;

	@FXML
	private ObservableList<Arbeidsgiver> listeallearbeidsgivere = FXCollections.observableArrayList();

	//Legg ut en stilling
	@FXML
	private TextField annonseTittel, arbeidssted, arbeidsvilkår, lønnsbetingelser, jobbtidtil, jobbtidfra;

	@FXML
	private TextArea kvalifikasjonen, beskrivelse;

	@FXML
	private DatePicker startDato, sluttDato;

	@FXML
	private Label registrert;

	@FXML
	private ToggleGroup type;

	@FXML
	private ComboBox<Arbeidsgiver> arbeidsgiver;

	@FXML
	private ComboBox jobbkategori;

	@FXML
	private RadioButton deltid, engasjement;

	@FXML
	private ObservableList<Arbeidsgiver> valgForArbeidsgiver;

	@FXML
	private ListView<nyStilling> lagtannonser;

	@FXML
	private ObservableList<nyStilling> nyStillinger;

	@FXML
	private ObservableList<nyStilling> nyStillingObservableList = FXCollections.observableArrayList();

	//Ledgie stillinger

	private FilteredList filter;

	@FXML
	private TextField sokbar;

	@FXML
	private ComboBox<Arbeidsgiver> arbeidsgivere;

	@FXML
	private Label feltStilllingsbeskrivelse,feltKvalifikasjoner;
	@FXML
	private TableView<nyStilling> TabellLedigestillinger;

	@FXML
	private TableColumn<nyStilling, String> KolonneArbgiver;

	@FXML
	private TableColumn KolonneSektor,KolonneArbsted,KolonneBransje,KolonneStdato,KolonneSldato,
			KolonneStillingtype,KolonneLonnbet,KolonneArbvilkar,KolonneKnavn,
			KolonneKstilling,KolonneKtelnr,KolonneAnonnsetittel,KolonneJobbTidStart,KolonneJobbTidSlutt;

	@FXML
	private ObservableList<nyStilling> alleStillinger = FXCollections.observableArrayList();

	@FXML

	private  ObservableList<Jobbsoker>kontraktforsokere=FXCollections.observableArrayList();

	@FXML

	private ListView<Jobbsoker>kontraksokere;

	//Jobbsøker
	@FXML
	private TextField Fornavn, Adresse, Postnr, Poststed, Telefonnummer, Epost, Stilling, ReferanseNavn,
			ReferanseTelefonnummer, Lønnskrav, utdanningsnavn, Firma;

	@FXML
	private Label lblJobbsoker, lblReferanse, lblJobberfaring;

	@FXML
	private DatePicker Fødselsdato, JobberfaringFDato, JobberfaringTdato;

	@FXML
	private ComboBox Utdannelse;

	@FXML
	private TableView referansetabell, JobbErfaringTabell;

	@FXML
	private TableColumn kolonnenavn, kolonnetelefonnummer, DatoFra, DatoTil, StillingNavn, FirmaNavn;

	@FXML
	private ListView<Jobbsoker> soktsokere;

	@FXML //Buttons for visible
	private Button referanseleggtil, SlettReferanse, LeggTilJobberfaring, SlettJobberfaring, Visjobberfaring,
			SkjulReferanser, VisReferanser, SkjulJobberfaring, OppdaterJobbsoker, SlettJobbsøker, skjulsokere,
			VisEndrejobbsoker, RedigerStillinger, SlettStillinger, EndreStillinger, SkjulStillinger, RedigerArbgiver,
			SlettArbeidsgiver, SkjulArbeidsgivere, EndreArbeidsgivere;

	//lister
	@FXML
	private ObservableList<String> Utdannelselister;

	private ObservableList<Jobbsoker> Jobbsooker;

	private ObservableList<Person> referanseren = FXCollections.observableArrayList();

	private ObservableList<Jobberfaring> jobberfaringer = FXCollections.observableArrayList();


	//Arbeidsforhold
	@FXML
	private  TextField SokAforhold;

	@FXML
	private  FilteredList filtrerAforhold;

	@FXML
	private	TableView TabellArbeidsforhold;

	@FXML

	private TableColumn KolonneAjobbsoker,KolonneAArbgiver,KolonneAtittel,KolonneAstdato,KolonneAsldato;

	@FXML
	private ObservableList<Kontrakt>komplettkontrakt=FXCollections.observableArrayList();


	//Arbeidsgiver - OnAction knapper
	@FXML
	public void Reg(ActionEvent event) {

		//Felter sjekkes før man kan registrere seg
		if (!sjekkFelt.regexSjekkBokstaver(bNavn) || !sjekkFelt.regexSjekkAdresse(bAdresse) || !sjekkFelt.regexSjekkBokstaver(bPoststed) || !sjekkFelt.regexSjekkPostnr(bPostnr) || !sjekkFelt.regexSjekkBokstaver(kFornavn) || !sjekkFelt.regexSjekkBokstaver(kStilling) || !sjekkFelt.regexSjekkTlfnr(kTlfnr)) {
			registrertArbeidsgiver.setText("Arbeidsgiver ble ikke registrert");
		} else {
			Adresse adresse = new Adresse(bAdresse.getText(), bPostnr.getText(), bPoststed.getText());
			RadioButton vSektor = (RadioButton) Asektor.getSelectedToggle();
			Person arbeidsgiver2 = new Person(kFornavn.getText(), kTlfnr.getText());

			Arbeidsgiver arbeidsgiver1 = new Arbeidsgiver(bNavn.getText(), vSektor.getText(), adresse, Bransje.getSelectionModel().getSelectedItem().toString(), kStilling.getText(),
					arbeidsgiver2);

			registrertArbeidsgiver.setText("Bedrift: " + bNavn.getText() + " har blitt registrert");


			//legger til i listview i arbeider giver
			allearbeidsgivere.add(arbeidsgiver1);

			//legger til i comboboxen i legg ut i stillinger
			valgForArbeidsgiver.add(arbeidsgiver1);

			//legger til i comboxen til arbeidsgiver under ledige stillinger
			listeallearbeidsgivere.add(arbeidsgiver1);

			//Nullstill
			bNavn.clear();
			bPostnr.clear();
			bPoststed.clear();
			bAdresse.clear();
			kStilling.clear();
			kFornavn.clear();
			kTlfnr.clear();

			arbedsgiverFil.add(arbeidsgiver1);
		}
	}

	@FXML
	public void RedigerArbgiver(ActionEvent event){ //Redigere arbeidsgiver

		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Ved å REDIGERE må du klikke 'FULLFØR ' for å bekrefte redigeringen, hvis ikke slettes jobbsøkeren fra systemet");
		alert.setContentText("Er du sikker på du vil utføre denne handlingen?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {

			Arbeidsgiver test = lagtarbeidsgivere.getSelectionModel().getSelectedItem();
			bNavn.setText(test.getbNavn());
			bAdresse.setText(test.getbAdresse().getAdresse());
			bPostnr.setText(test.getbAdresse().getPostnr());
			bPoststed.setText(test.getbAdresse().getPoststed());
			Bransje.setValue(test.getBransje());
			kFornavn.setText(test.getPerson().getFornavn());
			kTlfnr.setText(test.getPerson().getTelefonnummer());

			if (test.getSektor() == sOffentlig.getText()) {
				sOffentlig.setSelected(true);
			} else {
				sPrivat.setSelected(true);
			}

			kStilling.setText(test.getkStilling());

			//sletter arbeidsgiver fra listviewen
			allearbeidsgivere.remove(test);
			valgForArbeidsgiver.remove(test);
		}
	}

	@FXML
	public void SlettArbeidsgiver(ActionEvent event) {
		Arbeidsgiver test= lagtarbeidsgivere.getSelectionModel().getSelectedItem();
		allearbeidsgivere.remove(test);
		valgForArbeidsgiver.remove(test);
	}

	//Legg ut en stilling - OnAction knapper
	@FXML
	public void registrerJobb(ActionEvent event) {

		//feilmeldinger på felter  - har en if som sier fra dersom en feil oppstår, og dersom det oppstår løpes metodene gjennom feltene og viser hvor feilen ligger
		if (!sjekkFelt.regexSjekkBokstaver(annonseTittel) || !sjekkFelt.regexSjekkBokstaver(arbeidssted) || !sjekkFelt.regexSjekkDato(startDato, sluttDato) || !sjekkFelt.regexSjekkBokstaver(arbeidsvilkår) || !sjekkFelt.regexSjekkTall(lønnsbetingelser) || !sjekkFelt.regexSjekkTid(jobbtidfra) || !sjekkFelt.regexSjekkTid(jobbtidtil) || !sjekkFelt.regexSjekkerBokstaver(kvalifikasjonen) || !sjekkFelt.regexSjekkerBokstaver(beskrivelse)) {
			registrert.setText("Stillingen ble ikke registrert");
		} else {
			registrert.setText("Stillingen har blitt registrert");

			//Må sette radioButton slik , slik at vi kan få ut hva som blir valgt.
			RadioButton valgtType = (RadioButton) type.getSelectedToggle();

			//Her lager vi tilling-klassen og henter alle verdiene fra feltene ved Legg ut stilling.
			Dato datoForArbeid = new Dato(startDato.getValue(), sluttDato.getValue());

			Arbeidsgiver testarbgiver = arbeidsgiver.getSelectionModel().getSelectedItem();

			nyStilling nyStilling = new nyStilling(annonseTittel.getText(), testarbgiver,
					arbeidssted.getText(), datoForArbeid, jobbkategori.getSelectionModel().getSelectedItem().toString(),
					valgtType.getText(), arbeidsvilkår.getText(), lønnsbetingelser.getText(), jobbtidfra.getText(),
					jobbtidtil.getText(),
					kvalifikasjonen.getText(),
					beskrivelse.getText(),null);

			// alle stillingene blir lagt til i tabellen  til ledige stillinger
			alleStillinger.add(nyStilling);

			// blir lagt til i listviewen slik at man kan slette og redigere stillinger
			nyStillinger.add(nyStilling);

			//Setter alle verdiene til null.
			annonseTittel.clear();
			arbeidssted.clear();
			startDato.setValue(null);
			sluttDato.setValue(null);
			arbeidsvilkår.clear();
			lønnsbetingelser.clear();
			jobbtidfra.clear();
			jobbtidtil.clear();
			kvalifikasjonen.clear();
			beskrivelse.clear();

			nyStillingFil.add(nyStilling);
		}
	}


	@FXML
	public void RedigerStillinger(ActionEvent event) {

		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Ved å REDIGERE må du klikke 'FULLFØR ' for å bekrefte redigeringen, hvis ikke slettes jobbsøkeren fra systemet");
		alert.setContentText("Er du sikker på du vil utføre denne handlingen?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {

			nyStilling test = lagtannonser.getSelectionModel().getSelectedItem();
			annonseTittel.setText(test.getAnnonseTittel());
			arbeidsgiver.setValue(test.getArbeidsgiver());
			arbeidssted.setText(test.getArbeidssted());
			startDato.setValue(test.getDato().getFraDato());
			sluttDato.setValue(test.getDato().getTildato());
			jobbkategori.setValue(test.getJobbkategori());
			arbeidsvilkår.setText(test.getArbeidsVilkår());

			if (test.getStilingsType() == deltid.getText()) {
				deltid.setSelected(true);
			} else {
				engasjement.setSelected(true);
			}

			lønnsbetingelser.setText(test.getLønnsBetingelser());
			jobbtidfra.setText(test.getJobbTidfra());
			jobbtidtil.setText(test.getJobbTidtil());
			kvalifikasjonen.setText(test.getKvalifikasjoner());
			beskrivelse.setText(test.getStillingsBskrivelse());

			//fjerner objektet fra listview under legg ut stillinger
			nyStillinger.remove(test);

			//fjerner objektet fra tableview under ledige stillinger
			alleStillinger.remove(test);
		}
	}


	public void SlettStillinger(ActionEvent event) {
		nyStilling test = lagtannonser.getSelectionModel().getSelectedItem();

		//slettes fra listviewen til legg ut stillinger
		nyStillinger.remove(test);

		//slettes fra tableviewen i ledige stillinger
		alleStillinger.remove(test);
	}


	//Jobbsøker - OnAction knapper
	@FXML
	public void Sok(ActionEvent event) {

		//Sjekker om feltene stemmer før en registrerer seg.
		if (!sjekkFelt.regexSjekkBokstaver(Fornavn) || !sjekkFelt.regexSjekkFødselsDato(Fødselsdato) || !sjekkFelt.regexSjekkAdresse(Adresse) || !sjekkFelt.regexSjekkBokstaver(Poststed) || !sjekkFelt.regexSjekkPostnr(Postnr) || !sjekkFelt.regexSjekkTlfnr(Telefonnummer) || !sjekkFelt.regexSjekkTall(Lønnskrav) || !sjekkFelt.regexSjekkBokstaver(utdanningsnavn) || !sjekkFelt.regexSjekkEmail(Epost)) {
			lblJobbsoker.setText("Jobbsøkeren ble ikke registrert");
		} else {
			//Legger til referanse
			ObservableList<Person> jobbsokerreferanser = FXCollections.observableArrayList();

			for (int i = 0; i < referanseren.size(); i++) {
				jobbsokerreferanser.add(referanseren.get(i));
			}

			//Legger til jobberfaring
			ObservableList<Jobberfaring> jobberfaringsliste = FXCollections.observableArrayList();

			for (int i = 0; i < jobberfaringer.size(); i++) {

				jobberfaringsliste.add(jobberfaringer.get(i));
			}

			Person person1 = new Person(Fornavn.getText(), Telefonnummer.getText());
			Adresse adresse2 = new Adresse(Adresse.getText(), Postnr.getText(), Poststed.getText());

			Jobbsoker jobbsøker2 = new Jobbsoker(person1,
					Epost.getText(),
					Fødselsdato.getValue(),
					adresse2,
					Utdannelse.getSelectionModel().getSelectedItem().toString(),
					utdanningsnavn.getText(),
					Lønnskrav.getText(),
					jobbsokerreferanser, jobberfaringsliste);

			//legger til i jobbsøker listview
			Jobbsooker.add(jobbsøker2);

			//legger til i listvieween jobbsøker under ledige stillinger
			kontraktforsokere.add(jobbsøker2);


			lblJobbsoker.setText("Jobbsøkeren ble registrert");

			referanseren.clear();
			jobberfaringer.clear();
			Fornavn.clear();
			Adresse.clear();
			Telefonnummer.clear();
			Poststed.clear();
			Telefonnummer.clear();
			Fødselsdato.setValue(null);
			Epost.clear();
			Postnr.clear();
			Lønnskrav.clear();
			utdanningsnavn.clear();

			jobbsøkerFil.add(jobbsøker2);
		}
	}

	//OnAction knapper for referanse i Jobbsøker
	@FXML
	public void referanseleggtil(ActionEvent event) {

		//Legger ikke til referanser, dersom det er noe feil i feltene.
		if (!sjekkFelt.regexSjekkBokstaver(ReferanseNavn) || !sjekkFelt.regexSjekkTlfnr(ReferanseTelefonnummer)) {
			lblReferanse.setText("Feil i inputfelt for Referanser");
		} else {
			Person referanse1 = new Person(ReferanseNavn.getText(), ReferanseTelefonnummer.getText());
			referanseren.add(referanse1);
			ReferanseNavn.setText(null);
			ReferanseTelefonnummer.setText(null);
		}
	}

	@FXML
	public void SlettReferanse(ActionEvent event) {
		referansetabell.getItems().removeAll(referansetabell.getSelectionModel().getSelectedItem());
	}

	//OnAction knapper for jobberfaring i Jobbsøker
	@FXML
	public void LeggTilJobberfaring(ActionEvent event) {
		if (!sjekkFelt.regexSjekkBokstaver(Stilling) || !sjekkFelt.regexSjekkJobbDato(JobberfaringFDato, JobberfaringTdato) || !sjekkFelt.regexSjekkBokstaver(Firma)) {
			lblJobberfaring.setText("Feil i inputfelt for Jobberfaring");
		} else {

			Jobberfaring jobberfaring1 = new Jobberfaring(Stilling.getText(), Firma.getText(),
					JobberfaringFDato.getValue(), JobberfaringTdato.getValue());

			jobberfaringer.add(jobberfaring1);

			Stilling.setText(null);
			Firma.setText(null);
			JobberfaringFDato.setValue(null);
			JobberfaringTdato.setValue(null);
		}
	}

	@FXML
	public void SlettJobberfaring(ActionEvent event) {
		JobbErfaringTabell.getItems().removeAll(JobbErfaringTabell.getSelectionModel().getSelectedItem());
	}

	//Redigere jobbsøker
	@FXML
	public void OppdaterJobbsoker(ActionEvent event) {

		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Ved å REDIGERE må du klikke 'FULLFØR ' for å bekrefte redigeringen, hvis ikke slettes jobbsøkeren fra systemet");
		alert.setContentText("Er du sikker på du vil utføre denne handlingen?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {

			Jobbsoker test = soktsokere.getSelectionModel().getSelectedItem();

			Fornavn.setText(test.getPerson().getFornavn());
			Fødselsdato.setValue(test.getFødselsDato());
			Adresse.setText(test.getAdresse().adresse);
			Epost.setText(test.getEpost());
			Poststed.setText(test.getAdresse().getPoststed());
			Postnr.setText(test.getAdresse().getPostnr());
			Telefonnummer.setText(test.getPerson().getTelefonnummer());
			Lønnskrav.setText(test.getLønnskrav());
			utdanningsnavn.setText(test.getTypeutdanning());

			Utdannelse.setValue(test.getUtdannelse());

			referanseren.clear();
			jobberfaringer.clear();

			for (int i = 0; i < test.getReferanser().size(); i++) {
				referanseren.add(test.getReferanser().get(i));
			}

			for (int i = 0; i < test.getJobberfaring().size(); i++) {
				jobberfaringer.add(test.getJobberfaring().get(i));
			}

			Jobbsooker.remove(test);
			kontraktforsokere.remove(test);
		}
	}

	//sletter jobbsøkeren i systemet
	@FXML
	public void SlettJobbsøker(ActionEvent event) {
		Jobbsoker test = soktsokere.getSelectionModel().getSelectedItem();
		Jobbsooker.remove(test);
		kontraktforsokere.remove(test);
	}


	//LEDIGE stillinger
	@FXML
	public void sokbar(KeyEvent event){
		sokbar.textProperty().addListener((observable,oldvalue,newValue )->{

			filter.setPredicate((Predicate<?super nyStilling>)(nyStilling nyStilling1)->{

				if(newValue.isEmpty()||newValue==null){
					return true;
				}
				String nyverdi=newValue.toLowerCase();

				if (nyStilling1.getArbNavn().contains(nyverdi)){//contains
					return true;
				}

				if(nyStilling1.getAnnonseTittel().contains(nyverdi)){
					return  true;
				}

				if(nyStilling1.getArbSektor().contains(nyverdi)){
					return  true;
				}
				if(nyStilling1.getJobbkategori().contains(nyverdi)){
					return  true;
				}
				if(nyStilling1.getArbeidssted().contains(nyverdi)){
					return  true;
				}
				if(nyStilling1.getStartDatoen().toString().contains(nyverdi)){
					return  true;
				}
				if(nyStilling1.getSluttDatoen().toString().contains(nyverdi)){
					return  true;
				}
				if(nyStilling1.getStilingsType().contains(nyverdi)){
					return  true;
				}
				if(nyStilling1.getLønnsBetingelser().contains(nyverdi)){
					return  true;
				}
				if(nyStilling1.getArbeidsVilkår().contains(nyverdi)){
					return  true;
				}
				if(nyStilling1.getKontaktnavn().contains(nyverdi)){
					return  true;
				}
				if(nyStilling1.getKontaktstilling().contains(nyverdi)){
					return  true;
				}
				if(nyStilling1.getKontakttelnr().contains(nyverdi)){
					return  true;
				}
				if(nyStilling1.getJobbTidfra().contains(nyverdi)){
					return  true;
				}
				if(nyStilling1.getJobbTidtil().contains(nyverdi)){
					return  true;
				}
				return  false;
			});
		});

		SortedList sort=new SortedList(filter);
		sort.comparatorProperty().bind(TabellLedigestillinger.comparatorProperty());
		TabellLedigestillinger.setItems(sort);
	}

	@FXML
	public void arbeidsgivere(ActionEvent event){

		arbeidsgivere.valueProperty().addListener((observable,oldvalue,newValue )->{
			filter.setPredicate((Predicate<?super nyStilling>)(nyStilling nyStilling1)->{

				if (newValue==null){
					return true;
				}
				if (nyStilling1.getArbNavn().equals(newValue.getbNavn())){
					return true;
				}
				return  false;
			});
		});

		SortedList sort=new SortedList(filter);
		sort.comparatorProperty().bind(TabellLedigestillinger.comparatorProperty());
		TabellLedigestillinger.setItems(sort);
	}


	public  void sokeransatt(ActionEvent event){
		Jobbsoker sokere= kontraksokere.getSelectionModel().getSelectedItem();
		nyStilling stillinger=TabellLedigestillinger.getSelectionModel().getSelectedItem();
		Kontrakt kontrakt1=new Kontrakt(stillinger,sokere);
		komplettkontrakt.add(kontrakt1);

		//sletter alt fra systemet etter at en jobbsøker får en stilling

		alleStillinger.remove(stillinger);
		kontraktforsokere.remove(sokere);
		Jobbsooker.remove(sokere);
		nyStillinger.remove(stillinger);

	}

	//Arbeidsforhold
	@FXML

	public void SokAforhold(KeyEvent event){

		SokAforhold.textProperty().addListener((observable,oldvalue,newValue )->{
			filtrerAforhold.setPredicate((Predicate<?super Kontrakt>)(Kontrakt Kontrakt1)->{

				if(newValue.isEmpty()||newValue==null){
					return true;
				}
				String nyverdi=newValue.toLowerCase();

				if (Kontrakt1.getNavnJobbsoker().contains(nyverdi)){//contains
					return true;
				}
				if (Kontrakt1.getNavnArbeidsgiver().contains(nyverdi)){//contains
					return true;
				}
				if (Kontrakt1.getNavnAnonnsetittel().contains(nyverdi)){//contains
					return true;
				}
				if (Kontrakt1.getKontraktSTdato().toString().contains(nyverdi)){//contains
					return true;
				}
				if (Kontrakt1.getKontraktSLdato().toString().contains(nyverdi)){//contains
					return true;
				}

				return  false;
			});
		});
		SortedList sort=new SortedList(filtrerAforhold);
		sort.comparatorProperty().bind(TabellArbeidsforhold.comparatorProperty());
		TabellArbeidsforhold.setItems(sort);
	}


	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		//Arbeidsgiver
		Bransjelister = FXCollections.observableArrayList("Bil", "IT", "Olje og Gass", "Økonomi og regnskap");
		Bransje.setItems(Bransjelister);
		valgForArbeidsgiver=arbeidsgiver.getItems();



		//Ledige stillinger
		jobbkategori.setItems(Bransjelister);


		//Jobbsøker
		Utdannelselister = FXCollections.observableArrayList("Videregående", "Bachelor", "Master", "Doktorgrad");
		Utdannelse.setItems(Utdannelselister);

		// kolonner under referanser for jobbsøker
		kolonnenavn.setCellValueFactory(new PropertyValueFactory<>("fornavn"));
		kolonnetelefonnummer.setCellValueFactory(new PropertyValueFactory<>("telefonnummer"));
		referanseren=referansetabell.getItems();


		//Kollonner jobberfaring for jobbsøker
		FirmaNavn.setCellValueFactory(new PropertyValueFactory<>("firma"));
		StillingNavn.setCellValueFactory(new PropertyValueFactory<>("stilling"));
		DatoFra.setCellValueFactory(new PropertyValueFactory<>("fraDatoErfaring"));
		DatoTil.setCellValueFactory(new PropertyValueFactory<>("tilDatoErfaring"));
		JobbErfaringTabell.setItems(jobberfaringer);
		jobberfaringer=JobbErfaringTabell.getItems();

		Jobbsooker = soktsokere.getItems();


		//legg ut stilling
		nyStillinger = FXCollections.observableArrayList();
		allearbeidsgivere = FXCollections.observableArrayList();

		allearbeidsgivere = lagtarbeidsgivere.getItems();
		listeallearbeidsgivere=arbeidsgivere.getItems();
		nyStillinger = lagtannonser.getItems();


		//ledige stillinger
		KolonneArbgiver.setCellValueFactory(new PropertyValueFactory<>("arbNavn"));
		KolonneSektor.setCellValueFactory(new PropertyValueFactory<>("arbSektor"));
		KolonneArbsted.setCellValueFactory(new PropertyValueFactory<>("arbeidssted"));
		KolonneStdato.setCellValueFactory(new PropertyValueFactory<>("startDatoen"));
		KolonneSldato.setCellValueFactory(new PropertyValueFactory<>("sluttDatoen"));
		KolonneBransje.setCellValueFactory(new PropertyValueFactory<>("jobbkategori"));
		KolonneStillingtype.setCellValueFactory(new PropertyValueFactory<>("stilingsType"));
		KolonneLonnbet.setCellValueFactory(new PropertyValueFactory<>("lønnsBetingelser"));
		KolonneArbvilkar.setCellValueFactory(new PropertyValueFactory<>("arbeidsVilkår"));
		KolonneKnavn.setCellValueFactory(new PropertyValueFactory<>("kontaktnavn"));
		KolonneKtelnr.setCellValueFactory(new PropertyValueFactory<>("kontakttelnr"));
		KolonneKstilling.setCellValueFactory(new PropertyValueFactory<>("kontaktstilling"));
		KolonneAnonnsetittel.setCellValueFactory(new PropertyValueFactory<>("annonseTittel"));
		KolonneJobbTidStart.setCellValueFactory(new PropertyValueFactory<>("jobbTidfra"));
		KolonneJobbTidSlutt.setCellValueFactory(new PropertyValueFactory<>("jobbTidtil"));


		alleStillinger=TabellLedigestillinger.getItems();
		filter = new FilteredList(alleStillinger, e -> true);
		kontraktforsokere=kontraksokere.getItems();

		//Felter som allerede er valgt
		sOffentlig.setSelected(true);
		Bransje.setValue("IT");
		deltid.setSelected(true);
		jobbkategori.setValue("IT");
		Utdannelse.setValue("Videregående");

		Person person1=new Person("Mats","87654321");
		Adresse testAdresse= new Adresse("martin olsen vei 20","0987","oslo");
		Arbeidsgiver arbeidsgivertestverdi=new Arbeidsgiver("ManPower","offentlig",testAdresse,"IT","sjef",
				person1);
		arbeidsgiver.setValue(arbeidsgivertestverdi);
		arbeidsgivere.setValue(arbeidsgivertestverdi);

		//arbeidsforhold
		KolonneAjobbsoker.setCellValueFactory(new PropertyValueFactory<>("navnJobbsoker"));
		KolonneAArbgiver.setCellValueFactory(new PropertyValueFactory<>("navnArbeidsgiver"));
		KolonneAtittel.setCellValueFactory(new PropertyValueFactory<>("navnAnonnsetittel"));
		KolonneAstdato.setCellValueFactory(new PropertyValueFactory<>("kontraktSTdato"));
		KolonneAsldato.setCellValueFactory(new PropertyValueFactory<>("kontraktSLdato"));

		komplettkontrakt=TabellArbeidsforhold.getItems();
		filtrerAforhold=new FilteredList(komplettkontrakt,e->true);

	}

	public void Viskomplettledigestillinger (MouseEvent event) {
		if (TabellLedigestillinger.getSelectionModel().getSelectedItem() != null) {
			feltStilllingsbeskrivelse.setVisible(true);
			feltKvalifikasjoner.setVisible(true);
			nyStilling testNystilling = TabellLedigestillinger.getSelectionModel().getSelectedItem();
			feltStilllingsbeskrivelse.setText("Stillingsbeskrivelse:\n "+testNystilling.getStillingsBskrivelse());
			feltKvalifikasjoner.setText("Kvalifikasjoner:\n"+testNystilling.getKvalifikasjoner());

		}
	}

	@FXML //for å lagre til fil
	public void LagreTilFil(ActionEvent event) throws IOException, UlovligFilException, SkriverIkkeTIlFilException {

		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(new File(System.getProperty("user.home")));

		fc.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Excel files", "*.csv"),
				new FileChooser.ExtensionFilter("Serialisering files", "*.jobj"));
		File selectedFile = fc.showSaveDialog(null);

		if (selectedFile != null) {

			//Vi skriver til et excel ark
			skriverTilFil.skrivTilFil(selectedFile.getPath(), arbedsgiverFil, nyStillingFil, jobbsøkerFil);

			//vi skriver til et .jobj fil
			skrivTilSerialisering.skrivTilFil(selectedFile.getPath(), arbedsgiverFil, nyStillingFil, jobbsøkerFil);
		} else {
			alert.setHeaderText("Feilmelding!");
			alert.setContentText("Ingen fil ble valgt");
			alert.showAndWait();
		}

	}

	//for å lese fra fil
	public void LeseFraFilen(ActionEvent event) throws IOException, UlovligFilException, SkriverIkkeTIlFilException {
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(new File(System.getProperty("user.home")));

		fc.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Excel files", "*.csv"),
				new FileChooser.ExtensionFilter("Serialisering files", "*.jobj"));
		File selectedFile = fc.showOpenDialog(null);

		allearbeidsgivere.clear();
		nyStillinger.clear();
		Jobbsooker.clear();
		valgForArbeidsgiver.clear();
		lagtarbeidsgivere.getItems().clear();

		if (selectedFile != null) {

			//Les fra fil, .csv fil(Excel)
			lesFraFil.LesFraFile(selectedFile.getPath(), arbedsgiverFil, nyStillingFil, jobbsøkerFil);

			for (Arbeidsgiver a : arbedsgiverFil) {
				allearbeidsgivere.add(a);
			}
			for (nyStilling n : nyStillingFil) {
				nyStillinger.add(n);
			}
			for (Jobbsoker j : jobbsøkerFil) {
				Jobbsooker.add(j);
			}

			//Les fra fil, .jobj fil
			lesFraFilSerialisering.LesFraFile(selectedFile.getPath(), arbedsgiverFil, nyStillingFil, jobbsøkerFil);

			for(Arbeidsgiver a1: arbedsgiverFil) { //skrive det ut i tabellene for arbeidsgiver igjen
				valgForArbeidsgiver = FXCollections.observableArrayList();
				valgForArbeidsgiver = lagtarbeidsgivere.getItems();

				allearbeidsgivere = FXCollections.observableArrayList();
				allearbeidsgivere = lagtarbeidsgivere.getItems();
				allearbeidsgivere.add(a1);
			}

			for(int i=0; i<nyStillingFil.size(); i++){ //skrive det ut i tabellene for nyStilling igjen
				nyStillingObservableList.add(nyStillingFil.get(i));
				alleStillinger.add(nyStillingFil.get(i));
				TabellLedigestillinger.getItems().add(nyStillingFil.get(i));
			}

			lagtarbeidsgivere.setItems(allearbeidsgivere);
			arbeidsgiver.setItems(allearbeidsgivere);
			arbeidsgivere.setItems(allearbeidsgivere);
			lagtannonser.setItems(nyStillingObservableList);


			/*for(Jobbsoker jobb:jobbsøkerFil) { //skrive det ut i tabellene for jobbsøker igjen
				Jobbsooker.add(jobb);
				Jobbsooker = FXCollections.observableArrayList();
				Jobbsooker = soktsokere.getItems();
			}*/

			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/views/progressbartester.fxml"));
			Parent root = loader.load();
			stage.setScene(new Scene(root));
			stage.setTitle("Vikarbyrå");
			stage.setResizable(false);
			root.getStylesheets().add(String.valueOf(getClass().getClassLoader().getResource("test.css")));
			stage.show();


		} else {
			alert.setHeaderText("Feilmelding!");
			alert.setContentText("Ingen fil ble valgt");
			alert.showAndWait();
		}
	}


	//---Visible koder

	//Visible metoder for arbeidsgiver
	@FXML
	public void SkjulArbeidsgivere(ActionEvent event) {
		RedigerArbgiver.setVisible(false);
		SlettArbeidsgiver.setVisible(false);
		lagtarbeidsgivere.setVisible(false);
		SkjulArbeidsgivere.setVisible(false);
		EndreArbeidsgivere.setVisible(true);
	}

	@FXML
	public void EndreArbeidsgivere(ActionEvent event) {
		RedigerArbgiver.setVisible(true);
		SlettArbeidsgiver.setVisible(true);
		lagtarbeidsgivere.setVisible(true);
		SkjulArbeidsgivere.setVisible(true);
		EndreArbeidsgivere.setVisible(false);
	}

	//visible metode for Legg ut en stilling
	@FXML
	public void EndreStillinger(ActionEvent event) {
		lagtannonser.setVisible(true);
		RedigerStillinger.setVisible(true);
		SlettStillinger.setVisible(true);
		SkjulStillinger.setVisible(true);
		EndreStillinger.setVisible(false);
	}

	public void SkjulStillinger(ActionEvent event) {
		lagtannonser.setVisible(false);
		RedigerStillinger.setVisible(false);
		SlettStillinger.setVisible(false);
		SkjulStillinger.setVisible(false);
		EndreStillinger.setVisible(true);
	}

	//Ved oppdatering og slett av jobbsøkere
	@FXML
	public void VisEndrejobbsoker(ActionEvent event) {
		soktsokere.setVisible(true);
		OppdaterJobbsoker.setVisible(true);
		SlettJobbsøker.setVisible(true);
		skjulsokere.setVisible(true);
		VisEndrejobbsoker.setVisible(false);
	}

	//Visible koder for Jobbsøker
	//referanser
	@FXML
	public void VisReferanser(ActionEvent event) {
		ReferanseNavn.setVisible(true);
		ReferanseTelefonnummer.setVisible(true);
		referanseleggtil.setVisible(true);
		SlettReferanse.setVisible(true);
		referansetabell.setVisible(true);
		SkjulReferanser.setVisible(true);
		VisReferanser.setVisible(false);
	}

	@FXML
	public void SkjulReferanser(ActionEvent event) {
		ReferanseNavn.setVisible(false);
		ReferanseTelefonnummer.setVisible(false);
		referanseleggtil.setVisible(false);
		SlettReferanse.setVisible(false);
		referansetabell.setVisible(false);
		SkjulReferanser.setVisible(false);
		VisReferanser.setVisible(true);
	}

	// skjuljobbsøker
	@FXML
	public void skjulsokere(ActionEvent Event) {
		soktsokere.setVisible(false);
		OppdaterJobbsoker.setVisible(false);
		SlettJobbsøker.setVisible(false);
		skjulsokere.setVisible(false);
		VisEndrejobbsoker.setVisible(true);
	}

	//Dette gjelder for jobberfaring
	@FXML
	public void Visjobberfaring(ActionEvent event) {
		Firma.setVisible(true);
		Stilling.setVisible(true);
		JobberfaringFDato.setVisible(true);
		JobberfaringTdato.setVisible(true);
		JobbErfaringTabell.setVisible(true);
		LeggTilJobberfaring.setVisible(true);
		SlettJobberfaring.setVisible(true);
		SkjulJobberfaring.setVisible(true);
		Visjobberfaring.setVisible(false);
	}

	@FXML
	public void SkjulJobberfaring(ActionEvent event) {
		Firma.setVisible(false);
		Stilling.setVisible(false);
		JobberfaringFDato.setVisible(false);
		JobberfaringTdato.setVisible(false);
		JobbErfaringTabell.setVisible(false);
		LeggTilJobberfaring.setVisible(false);
		SlettJobberfaring.setVisible(false);
		SkjulJobberfaring.setVisible(false);
		Visjobberfaring.setVisible(true);
	}
}
