package ch.bzz.smartcredit.model;

public class KKarte {
    private String kkarteUUID;
    private String institut;
    private String kartenNummer;

    public KKarte() {

    }

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
