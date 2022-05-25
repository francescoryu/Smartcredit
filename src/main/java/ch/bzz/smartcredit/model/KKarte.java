package ch.bzz.smartcredit.model;

import ch.bzz.smartcredit.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @autor : Francesco Ryu
 * @date : 23.05.2022
 * @Version : 4.0
 */

public class KKarte {
    @JsonIgnore
    private Kunde kunde;
    private String kundeUUID;

    private String kkarteUUID;
    private String institut;
    private String kartenNummer;

    //-------------Getter und Setter----------------//

    public String getKundeUUID() {
        if (getKunde()== null) return null;
        return getKunde().getKundeUUID();
    }

    public void setKundeUUID(String kundeUUID) {
        setKunde(new Kunde());
        Kunde kunde = DataHandler.readKundeByUUID(kundeUUID);
        getKunde().setKundeUUID(kundeUUID);
        getKunde().setKunde(kunde.getKunde());
    }


    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public String getKKarteUUID() {
        return kkarteUUID;
    }

    public String getInstitut() {
        return institut;
    }

    public String getKartenNummer() {
        return kartenNummer;
    }

    public void setKKarteUUID(String KKarteUUID) {
        this.kkarteUUID = KKarteUUID;
    }

    public void setInstitut(String institut) {
        this.institut = institut;
    }

    public void setKartenNummer(String kartenNummer) {
        this.kartenNummer = kartenNummer;
    }

}
