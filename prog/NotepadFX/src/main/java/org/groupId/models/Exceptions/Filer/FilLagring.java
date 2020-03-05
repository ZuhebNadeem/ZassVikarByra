package org.groupId.models.Exceptions.Filer;

import org.groupId.models.Exceptions.UlovligFilException;
import org.groupId.models.Exceptions.Klasser.Arbeidsgiver;
import org.groupId.models.Exceptions.Klasser.Jobbsoker;
import org.groupId.models.Exceptions.Klasser.nyStilling;

import java.io.IOException;
import java.util.ArrayList;

//abstrakt klasse for å skrive til fil
public interface FilLagring {

    //Til csv, f.eks. Excel og bruker også for Skrive til .jobj fil med Javas støtte for serialisering
    void skrivTilFil(String path, ArrayList<Arbeidsgiver> nyArbeidsgiver,
                     ArrayList<nyStilling> nyeStillinger,
                     ArrayList<Jobbsoker> nyJobbsoker) throws IOException, UlovligFilException;

}