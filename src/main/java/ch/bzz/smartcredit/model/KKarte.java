package ch.bzz.smartcredit.model;

/**
 * @autor : Francesco Ryu
 * @date : 23.05.2022
 * @Version : 4.0
 */

public class KKarte {
    private String kkarteUUID;
    private String institut;
    private String kartenNummer;

    /**
     * Leerer Konstruktor
     */

    public KKarte() {

    }

    /**
     * Konstruktor mit Werten
     */

    public KKarte(String kkarteUUID, String institut, String kartenNummer) {

    }

    //-------------Getter und Setter----------------//

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
