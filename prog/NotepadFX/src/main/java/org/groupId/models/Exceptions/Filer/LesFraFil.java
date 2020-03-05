package org.groupId.models.Exceptions.Filer;

import org.groupId.models.Exceptions.Klasser.Jobbsoker;
import org.groupId.models.Exceptions.Klasser.nyStilling;
import org.groupId.models.Exceptions.SkriverIkkeTIlFilException;
import org.groupId.models.Exceptions.UlovligFilException;
import org.groupId.models.Exceptions.Klasser.Arbeidsgiver;

import java.io.IOException;
import java.util.ArrayList;

public interface LesFraFil {

    //metoden for å lese fra fil
    void LesFraFile(String path, ArrayList<Arbeidsgiver> nyArbeidsgiver,
                    ArrayList<nyStilling> nyeStillinger,
                    ArrayList<Jobbsoker> nyJobbsoker) throws IOException, UlovligFilException, SkriverIkkeTIlFilException;

}
