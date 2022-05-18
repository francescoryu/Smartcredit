package ch.bzz.smartcredit.model;

import java.util.List;

public class Kunde {

    private String kundeUUID;
    private List<KKarte> kartenListe;
    private String vorName;
    private String nachName;
    private int alter;




    //-------------Getter und Setter----------------//
    public String getKundeUUID() {
        return kundeUUID;
    }

    public List<KKarte> getKartenListe() {
        return kartenListe;
    }

    public String getVorName() {
        return vorName;
    }

    public String getNachName() {
        return nachName;
    }

    public int getAlter() {
        return alter;
    }

    public void setKundeUUID(String kundeUUID) {
        this.kundeUUID = kundeUUID;
    }

    public void setKartenListe(List<KKarte> kartenListe) {
        this.kartenListe = kartenListe;
    }

    public void setVorName(String vorName) {
        this.vorName = vorName;
    }

    public void setNachName(String nachName) {
        this.nachName = nachName;
    }

    public void setAlter(int alter) {
        this.alter = alter;
    }
}
