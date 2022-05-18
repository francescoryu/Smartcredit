package ch.bzz.smartcredit.model;

public class KKarte {
    private String KKarteUUID;
    private String institut;
    private String kartenNummer;


    //-------------Getter und Setter----------------//
    public String getKKarteUUID() {
        return KKarteUUID;
    }

    public String getInstitut() {
        return institut;
    }

    public String getKartenNummer() {
        return kartenNummer;
    }

    public void setKKarteUUID(String KKarteUUID) {
        this.KKarteUUID = KKarteUUID;
    }

    public void setInstitut(String institut) {
        this.institut = institut;
    }

    public void setKartenNummer(String kartenNummer) {
        this.kartenNummer = kartenNummer;
    }
}
