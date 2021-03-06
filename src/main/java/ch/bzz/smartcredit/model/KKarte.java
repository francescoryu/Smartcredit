package ch.bzz.smartcredit.model;

import ch.bzz.smartcredit.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.*;
import javax.ws.rs.FormParam;

/**
 * @autor : Francesco Ryu
 * @date : 14.06.2022
 * @Version : 5.0
 */

public class KKarte {

    @JsonIgnore
    private Kunde kunde;

    @NotEmpty
    @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    @FormParam("kundeUUID")
    private String kundeUUID;

    @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    @FormParam("kkarteUUID")
    private String kkarteUUID;

    @NotEmpty
    @Size(min=2, max=40)
    @FormParam("institut")
    private String institut;

    @NotEmpty
    @Pattern(regexp = "([0-9]{4} ){3}[0-9]{4}")
    @FormParam("kartenNummer")
    private String kartenNummer;

    //-------------Getter und Setter----------------//

    public String getKundeUUID() {
        if (getKunde()== null) return null;
        return kundeUUID;
    }

    public void setKundeUUID(String kundeUUID) {
        setKunde(new Kunde());
        Kunde kunde = DataHandler.readKundeByUUID(kundeUUID);
        getKunde().setKundeUUID(kundeUUID);
        getKunde().setVorName(kunde.getVorName());
        getKunde().setNachName(kunde.getNachName());
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
